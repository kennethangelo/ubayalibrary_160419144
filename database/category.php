<?php

$data = array(
    array(
        "id" => 1, "name" => "Historical Fiction",
        "description" => "Historical fiction is a literary genre where the story takes place in the past. Historical novels capture the details of the time period as accurately as possible for authenticity, including social norms, manners, customs, and traditions."
    ),

    array(
        "id" => 2, "name" => "Self-Help",
        "description" => "A self-help book is one that is written with the intention to instruct its readers on solving personal problems. Problem-focused self-help books offer advice on how to overcome specific issues like insomnia, stress, addiction, anxiety, and depression."
    ),

    array(
        "id" => 3, "name" => "Popular Science",
        "description" => "Popular science, sometimes called literature of science, is interpretation of science intended for a general audience."
    ),
);

if (isset($_GET['id'])) {
    $data = $data[$_GET['id']];
} else if (isset($_GET['categoryID'])) {
    $data = $data[$_GET['categoryID'] - 1];
}


echo json_encode($data);
