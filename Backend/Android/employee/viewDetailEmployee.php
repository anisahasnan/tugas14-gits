<?php 
 
 include 'dbconfig.php';
 
	//Importing database
	// require_once('koneksi.php');

if ($_SERVER['REQUEST_METHOD']=='GET'){

	//Mendapatkan Nilai Dari Variable ID Pegawai yang ingin ditampilkan
	$id = $_GET['id'];
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT * FROM employee WHERE id=$id";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($db,$sql);

	if($r){
		$data = mysqli_fetch_assoc($r);
		$response = $data;
		echo json_encode($response);
	}
	else{
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
	
	

	//Memasukkan Hasil Kedalam Array
	// $result = array();
	// $row = mysqli_fetch_array($r);
	// array_push($result,array(
	// 		"id"=>$row['id'],
	// 		"name"=>$row['name'],
	// 		"sex"=>$row['sex'],
	// 		"email"=>$row['email'],
	// 		"phone"=>$row['phone'],
	// 		"address"=>$row['address'],
	// 		"division"=>$row['division']
	// 	));
 
	// //Menampilkan dalam format JSON
	// echo json_encode($result);
	
	// mysqli_close($con);
?>