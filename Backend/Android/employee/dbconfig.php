<?php
 
 //Mendefinisikan Konstanta
 // define('HOST','localhost');
 // define('USER','root');
 // define('PASS','');
 // define('DB','android_db');
 
 $server = "localhost";
 $user = "root";
 $password = "";
 $nama_database = "android_db";

 //membuat koneksi dengan database
 $db = mysqli_connect($server, $user, $password, $nama_database);

 if( !$db ){
    die("Gagal terhubung dengan database: " . mysqli_connect_error());
}

 // //membuat koneksi dengan database
 // $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');


 ?>
 