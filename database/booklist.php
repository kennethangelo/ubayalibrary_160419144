<?php

$data = array(
    array("id" => 1, "name" => "My Top 3 Favorite Books", "desc" => "Berisi kumpulan buku yang saya suka selama tahun 2020.", "date_added" => "2020-06-05", "username" => "kennethangelo"),
    array("id" => 2, "name" => "Ubaya Book Awards 2021", "desc" => "Berisi kumpulan buku yang meraih penghargaan yang diadakan Ubaya setiap tahunnya untuk memperkenalkan literatur terhadap mahasiswa.", "date_added" => "2021-07-10", "username" => "adamlevine"),
    array("id" => 3, "name" => "Pulitzer Book Prize 2021", "desc" => "Berisi kumpulan buku yang meraih penghargaan bergengsi di bidang literatur, Pulitzer Book Prize.", "date_added" => "2021-10-23", "username" => "taylorswift"),
    array("id" => 4, "name" => "Ubaya SatuBuku", "desc" => "Berisi kumpulan buku yang dipilih Universitas Surabaya untuk acara SatuBuku.", "date_added" => "2022-02-27", "username" => "johnsonmike"),
);

function search($type, $id, $data)
{
    if ($type == "username") {
        $res = [];
        foreach ($data as $key => $val) {
            if ($val['username'] == $id) {
                $res[] = $data[$key];
            }
        }
    } else if ($type = "id") {
        $res = "";
        foreach ($data as $key => $val) {
            if ($val['id'] == $id) {
                $res = $data[$key];
            }
        }
    }
    return $res;
}

if (isset($_GET['username'])) {
    $data = search("username", $_GET['username'], $data);
} else if (isset($_GET['booklistID'])) {
    $data = search("id", $_GET['booklistID'], $data);
}

echo json_encode($data);
