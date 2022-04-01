package id.ac.ubaya.informatika.ubayalibrary_160419144.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.ubayalibrary_160419144.R
import id.ac.ubaya.informatika.ubayalibrary_160419144.model.Review
import id.ac.ubaya.informatika.ubayalibrary_160419144.util.loadImage
import kotlinx.android.synthetic.main.review_list_item.view.*

class ReviewAdapter(val reviewList:ArrayList<Review>): RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.review_list_item, parent, false)

        return ReviewViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviewList[position]
        with(holder.view){
            txtReviewUsername.text = review.user!!.username
            txtReviewName.text = review.user.name
            txtReviewContent.text= review.content
            txtReviewAdded.text = review.date
            ratingReview.setIsIndicator(false)
            ratingReview.numStars = review.stars!!.toInt()
            txtReviewUsername.setOnClickListener {
                val action =
                    ReviewFragmentDirections.actionReviewToDetailProfileFragment(review.user.username.toString())
                Navigation.findNavController(it).navigate(action)
            }

            imgReviewUsername.loadImage(review.user.imgUrl, pbReviewImg, 200,200)
        }
    }

    override fun getItemCount() = reviewList.size

    fun updateReviewList(newReviewList: List<Review>) {
        reviewList.clear()
        reviewList.addAll(newReviewList)
        notifyDataSetChanged()
    }
}