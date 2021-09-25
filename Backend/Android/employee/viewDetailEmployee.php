<?php 
 
include 'dbconfig.php';

if ($_SERVER['REQUEST_METHOD']=='GET'){

	// Mendapatkan ID
	$id = $_GET['id'];
	
	// Membuat SQL Query
	$sql = "SELECT * FROM employee WHERE id=$id";
	
	// Mendapatkan Hasil 
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
?>