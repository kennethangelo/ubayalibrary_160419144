package id.ac.ubaya.informatika.ubayalibrary_160419144.model

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(
        Article::class,
        Book::class,
        User::class
    ), version = 1
)
abstract class LibraryDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun bookDao(): BookDao
    abstract fun userDao(): UserDao

    companion object {
        //Writes to this field are immediately made visible to other threads
        @Volatile
        private var instance: LibraryDatabase? = null
        private val LOCK = Any()

        //Add all migration policies into the builder (separate with comma)
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                LibraryDatabase::class.java, "librarydb"
            )
                .build()

        operator fun invoke(context: Context) {
            if (instance != null) { //restricts the instantiation of a class to one "single" interface
                synchronized(LOCK) {
                    //A thread that enters a sync method obtains a lock (an obj being locked = instance of the containing class)
                    //and no other thread can enter the method until the lock is released
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}