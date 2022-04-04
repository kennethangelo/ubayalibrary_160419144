<?php

$data = array(
    array("id"=>1, "fullname"=>"Leila S. Chudori", "date_of_birth"=>"12 December 1962", 
    "bio"=>"Leila Salikha Chudori studied at Trent University in Ontario, Canada, graduating in 1988. She worked for the magazines Jakarta Jakarta and Tempo. Her first stories were published in children's magazines Si Kuncung, Kawanku and Hai when she was only 12. Chudori later wrote scripts for the television series Dunia Tanpa Koma.",
    "photo_url"=>"https://awsimages.detik.net.id/community/media/visual/2018/01/02/94689c58-cb80-47a3-abfd-642eb0a4f782.jpg"),
    
    array("id"=>2, "fullname"=>"Brian Christian", "date_of_birth"=>"24 June 1986", 
    "bio"=>"Brian Christian is an American non-fiction author, poet, programmer and researcher, best known for the two bestselling books The Most Human Human and Algorithms to Live By.", 
    "photo_url"=>"https://i1.sndcdn.com/artworks-pQLuxO54pzlRncOl-Rc6lSA-t500x500.jpg"),
    
    array("id"=>3, "fullname"=>"James Clear", "date_of_birth"=>"22 January 1986", 
    "bio"=>"James Clear is a writer and speaker focused on habits, decision making, and continuous improvement. He is the author of the no. 1 New York Times bestseller, Atomic Habits. The book has sold over 5 million copies worldwide and has been translated into more than 50 languages.", 
    "photo_url"=>"https://www.jordanharbinger.com/wp-content/uploads/2018/10/108-james-clear-showart.jpg"),
    
    array("id"=>4, "fullname"=>"Morgan Housel", "date_of_birth"=>"24 February 1987", 
    "bio"=>"Morgan Housel is a partner at The Collaborative Fund. His book The Psychology of Money has sold over one million copies and has been translated into 46 languages. He is a two-time winner of the Best in Business Award from the Society of American Business Editors and Writers, winner of the New York Times Sidney Award, and a two-time finalist for the Gerald Loeb Award for Distinguished Business and Financial Journalism. ", 
    "photo_url"=>"https://podcast-notes-uploads.imgix.net/2020/09/morgan-housel-400x400-1.jpg"),
    
    array("id"=>5, "fullname"=>"Henry Manampiring", "date_of_birth"=>"20 June 1978", 
    "bio"=>"Henry Manampiring is the author of Filosofi Teras (Philosophy from the Porch) and through his book has played a huge role in introducing Stoicism to his home country of Indonesia.", 
    "photo_url"=>"https://socialmediaweek.org/conference/files/2017/07/SMW17-SpeakerProfile-HenryM.jpg"),
);

if(isset($_GET['id'])){
    $data = $data[$_GET['id']-1];
}
echo json_encode($data);
