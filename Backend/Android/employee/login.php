<?php

include 'dbconfig.php';

if ($_SERVER['REQUEST_METHOD']=='POST'){

    $username = $_POST['username'];
    $password = $_POST['password'];

    $sql = "SELECT * FROM user WHERE username = '$username' and password = '$password'";

    $query = mysqli_query($db, $sql);
    $user = mysqli_fetch_assoc($query);

    $id_user = "".$user['id'];
    $username_user = "".$user['username'];

    if (!empty($user)) {
        $response["error"] = false;
        $response["message"] = "Berhasil Login";
        $response["idUser"] = $id_user;
        $response["username"] = $username_user;
        echo json_encode($response);
    } else{
        $response["error"] = true;
        $response["message"] = "Gagal Login! Username atau password salah";
        echo json_encode($response);
    }

} else {
    $response["error"] = true;
    $response["message"] = "404";

    echo json_encode($response);
}
?>
