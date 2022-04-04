<?php
$data = array(
    array(
        "username" => "kennethangelo", "password" => "12345", "name" => "Kenneth", "date_of_birth" => "24 June 2001", "created_at" => "10 May 2011", "bio" => "Chill and relax.", "photo_url" => "https://images.unsplash.com/photo-1570295999919-56ceb5ecca61?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bWFsZXxlbnwwfHwwfHw%3D",
        "headerImg" => "https://i.pinimg.com/originals/77/fb/a6/77fba6d0c0f978ff506e3dfc00a1bb2f.jpg"
    ),
    array(
        "username" => "johnsonmike", "password" => "12345", "name" => "Mike Johnson", "date_of_birth" => "20 May 1995", "created_at" => "02 March 2020", "bio" => "A professional software engineer in Alphabet.", "photo_url" => "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8",
        "headerImg" => "http://yarori.sideka.id/wp-content/uploads/sites/6234/2019/03/header-image-1-2.png"
    ),
    array(
        "username" => "ciera080101", "password" => "12345", "name" => "Sierra Doe", "date_of_birth" => "31 December 1992", "created_at" => "10 September 2021", "bio" => "18! Live, laugh, love.", "photo_url" => "https://randomuser.me/api/portraits/women/6.jpg",
        "headerImg" => "https://media.istockphoto.com/photos/the-picturesque-mountain-landscape-on-the-sunset-background-picture-id1327276218?b=1&k=20&m=1327276218&s=170667a&w=0&h=j_6Bu9P8PvHu8wQvtAyxUpfBeO189peY4F1C086_7lU="
    ),
    array(
        "username" => "taylorswift13", "name" => "Taylor Swift", "password" => "12345", "date_of_birth" => "13 December 1989", "created_at" => "13 December 2007", "bio" => "Happy, lonely, and confused at the same time.", "photo_url" => "https://i.pinimg.com/originals/95/8d/aa/958daae8d0bc682cb71e5ce32ec927d5.jpg",
        "headerImg" => "https://64.media.tumblr.com/15713e5a83adb7ab207378f44add46e0/8f86b1d20925b054-4b/s1280x1920/0f3637acb9d4f34ede2f657029111cb12d51cb02.png"
    ),
    array(
        "username" => "arianagrande", "name" => "Ariana Grande", "password" => "12345", "date_of_birth" => "01 September 1989", "created_at" => "01 January 2019", "bio" => "Switching position just for you!", "photo_url" => "https://data.whicdn.com/images/340331006/original.jpg",
        "headerImg" => "https://i.pinimg.com/originals/09/98/94/099894800469994bc38970b5f3dc4f21.png"
    ),
);

function searchUser($username, $data)
{
    $res = "";
    foreach ($data as $key => $val) {
        if ($val['username'] == $username) {
            $res = $data[$key];
        }
    }
    return $res;
}

if (isset($_GET['username'])) {
    $data = searchUser($_GET['username'], $data);
}

echo json_encode($data);
