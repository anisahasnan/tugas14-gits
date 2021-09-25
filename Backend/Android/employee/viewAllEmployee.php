<?php

include "dbconfig.php";

if ($_SERVER['REQUEST_METHOD']=='GET'){

	// Membuat SQL Query
	$sql = "SELECT * FROM employee";

	// Mendapatkan Hasil
	$r = mysqli_query($db,$sql);

	while($data = mysqli_fetch_assoc($r)){
	        $arrayJson[] = $data;
	    }
	    $response = $arrayJson;
	    echo json_encode($response);

} 
else {
    $response["error"] = true;
    $response["message"] = "404";

    echo json_encode($response);
}
?>
