<?php
 
include 'dbconfig.php';
 
if($_SERVER['REQUEST_METHOD']=='POST'){

	//Import File Koneksi database
	// require_once('koneksi.php');

	//Mendapatkan Nilai Variable
	$name = $_POST['name'];
	$sex = $_POST['sex'];
	$email = $_POST['email'];
	$phone = $_POST['phone'];
	$address = $_POST['address'];
	$division = $_POST['division'];

	//Pembuatan Syntax SQL
	$sql = "INSERT INTO employee (name, sex, email, phone, address, division) VALUES ('$name','$sex','$email', '$phone', '$address', '$division')";

	// //Eksekusi Query database
	// if(mysqli_query($con,$sql)){
	// 	echo 'Berhasil Menambahkan Employee';
	// }else{
	// 	echo 'Gagal Menambahkan Employee';
	// }

	$query = mysqli_query($db, $sql);
    if ($query){
        $response["error"] = false;
        $response["message"] = "Berhasil";
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

// mysqli_close($con);
// }
?>