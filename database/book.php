<?php

$data = array(
    array(
        "id" => 1, "title" => "Laut Bercerita", "imgUrl" => "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1516602134l/36393774._SX318_.jpg", "description" => "Laut Bercerita menceritakan terkait perilaku kekejaman dan kebengisan yang dirasakan oleh kelompok aktivis mahasiswa di masa Orde Baru. Tidak hanya itu, novel ini pun merenungkan kembali akan hilangnya 13 aktivis, bahkan sampai saat ini belum juga ada yang mendapatkan petunjuknya.",
        "total_pages" => 389, "author" => ["id" => 1, "fullname" => "Leila S. Chudori"], "category" => ["id" => 1, "name" => "Historical Fiction"], "publisher" => ["id" => 1, "name" => "Gramedia Pustaka Utama"]
    ),

    array(
        "id" => 2, "title" => "Algorithms to Live By: The Computer Science of Human Decisions", "imgUrl" => "https://images-na.ssl-images-amazon.com/images/I/415UY+ToE-L._SX329_BO1,204,203,200_.jpg", "description" => "An exploration of how computer algorithms can be applied to our everyday lives to solve common decision-making problems and illuminate the workings of the human mind. What should we do, or leave undone, in a day or a lifetime? How much messiness should we accept? What balance of the new and familiar is the most fulfilling? These may seem like uniquely human quandaries, but they are not. Computers, like us, confront limited space and time, so computer scientists have been grappling with similar problems for decades. And the solutions they’ve found have much to teach us. In a dazzlingly interdisciplinary work, Brian Christian and Tom Griffiths show how algorithms developed for computers also untangle very human questions. They explain how to have better hunches and when to leave things to chance, how to deal with overwhelming choices and how best to connect with others. From finding a spouse to finding a parking spot, from organizing one’s inbox to peering into the future, Algorithms to Live By transforms the wisdom of computer science into strategies for human living.",
        "total_pages" => 368, "author" => ["id" => 2, "fullname" => "Brian Christian"], "category" => ["id" => 3, "name" => "Popular Science"], "publisher" => ["id" => 2, "name" => "Harper Collins Books"]
    ),

    array(
        "id" => 3, "title" => "Atomic Habits: An Easy & Proven Way to Build Good Habits & Break Bad Ones",
        "imgUrl" => "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1535115320l/40121378._SY475_.jpg",
        "description" => "No matter your goals, Atomic Habits offers a proven framework for improving--every day. James Clear, one of the world's leading experts on habit formation, reveals practical strategies that will teach you exactly how to form good habits, break bad ones, and master the tiny behaviors that lead to remarkable results.",
        "total_pages" => 319, "author" => ["id" => 3, "fullname" => "James Clear"], "category" => ["id" => 2, "name" => "Self-Help"], "publisher" => ["id" => 3, "name" => "Penguin Books"]
    ),

    array(
        "id" => 4, "title" => "The Psychology of Money", "imgUrl" => "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1581527774l/41881472._SY475_.jpg", "description" => "Timeless lessons on wealth, greed, and happiness doing well with money isn’t necessarily about what you know. It’s about how you behave. And behavior is hard to teach, even to really smart people. How to manage money, invest it, and make business decisions are typically considered to involve a lot of mathematical calculations, where data and formulae tell us exactly what to do. But in the real world, people don’t make financial decisions on a spreadsheet. They make them at the dinner table, or in a meeting room, where personal history, your unique view of the world, ego, pride, marketing, and odd incentives are scrambled together. In the psychology of money, the author shares 19 short stories exploring the strange ways people think about money and teaches you how to make better sense of one of life’s most important matters.",
        "total_pages" => 252, "author" => ["id" => 4, "fullname" => "Morgan Housel"], "category" => ["id" => 3, "name" => "Popular Science"], "publisher" => ["id" => 3, "name" => "Penguin Books"]
    ),

    array(
        "id" => 5, "title" => "Filosofi Teras", "imgUrl" => "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1548033656l/42861019._SX318_.jpg", "description" => "Apakah kamu sering merasa khawatir akan banyak hal? baperan? susah move-on? mudah tersinggung dan marah-marah di social media maupun dunia nyata? Lebih dari 2.000 tahun lalu, sebuah mazhab filsafat menemukan akar masalah dan juga solusi dari banyak emosi negatif. Stoisisme, atau Filosofi Teras, adalah filsafat Yunani-Romawi kuno yang bisa membantu kita mengatasi emosi negatif dan menghasilkan mental yang tangguh dalam menghadapi naik-turunnya kehidupan. Jauh dari kesan filsafat sebagai topik berat dan mengawang-awang, Filosofi Teras justru bersifat praktis dan relevan dengan kehidupan Generasi Milenial dan Gen-Z masa kini.",
        "total_pages" => 344, "author" => ["id" => 5, "fullname" => "Henry Manampiring"], "category" => ["id" => 2, "name" => "Self-Help"], "publisher" => ["id" => 4, "name" => "PT Elex Media Komputindo"]
    ),
);

function search($type, $id, $data)
{
    $res = [];
    if ($type == "Author") {
        foreach ($data as $key => $val) {
            if ($val['author']['id'] == $id) {
                $res[] = $data[$key];
            }
        }
    } else if ($type == "Category") {
        foreach ($data as $key => $val) {
            if ($val['category']['id'] == $id) {
                $res[] = $data[$key];
            }
        }
    }
    return $res;
}

if (isset($_GET['id'])) {
    $data = $data[$_GET['id'] - 1];
} else if (isset($_GET['authorID'])) {
    $data = search("Author", $_GET['authorID'], $data);
} else if (isset($_GET['categoryID'])) {
    $data = search("Category", $_GET['categoryID'], $data);
}
echo json_encode($data);
