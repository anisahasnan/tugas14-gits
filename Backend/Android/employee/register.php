<?php

include 'dbconfig.php';

if ($_SERVER['REQUEST_METHOD']=='POST'){

    $username = $_POST['username'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    $sql1 = "SELECT username FROM user WHERE username='$username'";
    $sql2 = "SELECT email FROM user WHERE email='$email'";

    $query1 = mysqli_query($db, $sql1);
    $username_query = mysqli_fetch_assoc($query1);
    $query2 = mysqli_query($db, $sql2);
    $email_query = mysqli_fetch_assoc($query2);

    if (!empty($username_query)){
        $response["error"] = true;
        $response["message"] = "Username sudah ada";
        echo json_encode($response);  
    } elseif(!empty($email_query)){
    	$response["error"] = true;
        $response["message"] = "Email sudah ada";
        echo json_encode($response); 
    } else{
        $sql = "INSERT INTO user (username, email, password) VALUE ('$username', '$email', '$password')";

        $query = mysqli_query($db, $sql);

        if ($query){
            $response["error"] = false;
            $response["message"] = "Berhasil Registrasi";
            echo json_encode($response);
        } else{
            $response["error"] = true;
            $response["message"] = "Gagal Registrasi";
            echo json_encode($response);
        }
    }
}
else{
    $response["error"] = true;
    $response["error_msg"] = "404";

    echo json_encode($response);
}
?>