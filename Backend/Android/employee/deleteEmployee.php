<?php 
 
include 'dbconfig.php';
 
if($_SERVER['REQUEST_METHOD']=='DELETE'){

    // Mendapatkan Nilai ID
    $id = $_GET['id'];


    // Membuat SQL Query
    $sql = "DELETE FROM employee WHERE id='$id'";

    $r = mysqli_query($db, $sql);

    if ($r){
        $response["error"] = false;
        $response["message"] = "Berhasil";
        $response["id"] = $id;
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