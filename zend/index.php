<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "test";

$conn = new PDO("mysql:host=$servername;dbname=test", $username, $password);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    echo "Connected successfully"; 

// for ($i = 1000; $i < 2000; $i++) {
// 	$stmt = $conn->prepare ("INSERT INTO user (name, email, password) VALUES (?, ?, ?)");
	
// 	$name = "User ".$i;
// 	$email = "user".$i.'@gmail.com';
// 	$pass = "1";
// 	$stmt->bindParam(1, $name);
// 	$stmt->bindParam(2, $email);
// 	$stmt->bindParam(3, $pass);
// 	$stmt -> execute();
// }

// $conn->close();
echo "inser success";
