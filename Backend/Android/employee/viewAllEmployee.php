<?php

include "dbconfig.php";

// require_once('koneksi.php');

if ($_SERVER['REQUEST_METHOD']=='GET'){
	//Membuat SQL Query
	$sql = "SELECT * FROM employee";

	//Mendapatkan Hasil
	$r = mysqli_query($db,$sql);

	//Membuat Array Kosong 
	// $result = array();

	// while($row = mysqli_fetch_array($r)){
		
	// 	//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
	// 	array_push($result,array(
	// 		"id"=>$row['id'],
	// 		"name"=>$row['name'],
	// 		"division"=>$row['division']
	// 	));

	while($data = mysqli_fetch_assoc($r)){
	        $arrayJson[] = $data;
	    }
	    $response = $arrayJson;
	    echo json_encode($response);

} else {
    $response["error"] = true;
    $response["message"] = "404";

    echo json_encode($response);
}

// //Menampilkan Array dalam Format JSON
// echo json_encode($result);

// mysqli_close($con);
?>
