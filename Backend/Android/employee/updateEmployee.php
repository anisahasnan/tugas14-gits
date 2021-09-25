<?php 
 
include 'dbconfig.php';

if($_SERVER['REQUEST_METHOD']=='POST'){

    // Mendapatkan Nilai Dari Variable 
    $id = $_POST['id'];

    $email = $_POST['email'];
    $phone = $_POST['phone'];
    $address = $_POST['address'];
    $division = $_POST['division'];


    // Membuat SQL Query
    $sql = "UPDATE employee SET email = '$email', phone = '$phone', address = '$address', division = '$division' WHERE id = '$id';";

    $r = mysqli_query($db,$sql);

    if ($r){
            $response["error"] = false;
            $response["message"] = "Berhasil";
            // $response["id"] = $id;
            echo json_encode($response);
        } else{
            $response["error"] = true;
            $response["message"] = "Gagal Mengirim";
            echo json_encode($response);
        }
}
else{
    $response["error"] = true;
    $response["message"] = "404";

    echo json_encode($response);
}
?>