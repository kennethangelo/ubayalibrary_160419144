<?php

$data = array(
    array("username" => "kennethangelo", "follow" => ["username" => "johnsonmike", "name" => "Mike Johnson", "photo_url" => "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8"]),
    array("username" => "kennethangelo", "follow" => ["username" => "ciera080101", "name" => "Sierra Doe", "photo_url" => "https://randomuser.me/api/portraits/women/6.jpg"]),
    array("username" => "kennethangelo", "follow" => ["username" => "taylorswift13", "name" => "Taylor Swift", "photo_url" => "https://i.pinimg.com/originals/95/8d/aa/958daae8d0bc682cb71e5ce32ec927d5.jpg"]),
    array("username" => "kennethangelo", "follow" => ["username" => "arianagrande", "name" => "Ariana Grande", "photo_url" => "https://data.whicdn.com/images/340331006/original.jpg"]),

    array("username" => "johnsonmike", "follow" => ["username" => "kennethangelo", "name" => "Kenneth", "photo_url" => "https://images.unsplash.com/photo-1570295999919-56ceb5ecca61?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bWFsZXxlbnwwfHwwfHw%3D"]),
    array("username" => "johnsonmike", "follow" => ["username" => "ciera080101", "name" => "Sierra Doe", "photo_url" => "https://randomuser.me/api/portraits/women/6.jpg"]),
    array("username" => "johnsonmike", "follow" => ["username" => "taylorswift13", "name" => "Taylor Swift", "photo_url" => "https://i.pinimg.com/originals/95/8d/aa/958daae8d0bc682cb71e5ce32ec927d5.jpg"]),
    array("username" => "johnsonmike", "follow" => ["username" => "arianagrande", "name" => "Ariana Grande", "photo_url" => "https://data.whicdn.com/images/340331006/original.jpg"]),

    array("username" => "ciera080101", "follow" => ["username" => "kennethangelo", "name" => "Kenneth", "photo_url" => "https://images.unsplash.com/photo-1570295999919-56ceb5ecca61?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bWFsZXxlbnwwfHwwfHw%3D"]),
    array("username" => "ciera080101", "follow" => ["username" => "johnsonmike", "name" => "Mike Johnson", "photo_url" => "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8"]),
    array("username" => "ciera080101", "follow" => ["username" => "taylorswift13", "name" => "Taylor Swift", "photo_url" => "https://i.pinimg.com/originals/95/8d/aa/958daae8d0bc682cb71e5ce32ec927d5.jpg"]),
    array("username" => "ciera080101", "follow" => ["username" => "arianagrande", "name" => "Ariana Grande", "photo_url" => "https://data.whicdn.com/images/340331006/original.jpg"]),

    array("username" => "taylorswift13", "follow" => ["username" => "kennethangelo", "name" => "Kenneth", "photo_url" => "https://images.unsplash.com/photo-1570295999919-56ceb5ecca61?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bWFsZXxlbnwwfHwwfHw%3D"]),
    array("username" => "taylorswift13", "follow" => ["username" => "johnsonmike", "name" => "Mike Johnson", "photo_url" => "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8"]),
    array("username" => "taylorswift13", "follow" => ["username" => "ciera080101", "name" => "Sierra Doe", "photo_url" => "https://randomuser.me/api/portraits/women/6.jpg"]),
    array("username" => "taylorswift13", "follow" => ["username" => "arianagrande", "name" => "Ariana Grande", "photo_url" => "https://data.whicdn.com/images/340331006/original.jpg"]),

    array("username" => "arianagrande", "follow" => ["username" => "kennethangelo", "name" => "Kenneth", "photo_url" => "https://images.unsplash.com/photo-1570295999919-56ceb5ecca61?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bWFsZXxlbnwwfHwwfHw%3D"]),
    array("username" => "arianagrande", "follow" => ["username" => "johnsonmike", "name" => "Mike Johnson", "photo_url" => "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8"]),
    array("username" => "arianagrande", "follow" => ["username" => "ciera080101", "name" => "Sierra Doe", "photo_url" => "https://randomuser.me/api/portraits/women/6.jpg"]),
    array("username" => "arianagrande", "follow" => ["username" => "taylorswift13", "name" => "Taylor Swift", "photo_url" => "https://i.pinimg.com/originals/95/8d/aa/958daae8d0bc682cb71e5ce32ec927d5.jpg"]),
);

function searchFriends($username, $data)
{
    $res = [];
    foreach ($data as $key => $val) {
        if ($val['username'] == $username) {
            $res[] = $data[$key]['follow'];
        }
    }
    return $res;
}

if (isset($_GET['username'])) {
    $data = searchFriends($_GET['username'], $data);
}

echo json_encode($data);
