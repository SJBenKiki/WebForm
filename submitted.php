<!DOCTYPE html>
<HTML lang="en">

<head>
<meta charset="utf-8">
<link href="stylesheet.css" rel="stylesheet">
 <title>Part Time Professor Form</title>
 
</head><!-- end head div -->

<body>






<div class="wrapper">

<?php

$name = $_POST['name'];
$tele = $_POST['telephone'];
$date = $_POST['date'];
$available = $_POST['available'];
$course = $_POST['course'];
$section = $_POST['section'];
$title = $_POST['title'];
$work = $_POST['workArea'];

echo "Teacher information </br>";
echo "</br>";
echo "Name: " . $name . "</br>";
echo "Telephone: " . $tele . "</br>";

echo "Date submitted: " . $date . "</br>";

echo "</br>";
echo "Teacher availibility: </br>";
foreach($available as $item){
	echo $item;
	echo "</br>";
}
echo "</br>";

echo $name . " is interested in the following course(s): </br>";
for ($i = 0; $i < count($course); $i++){
	echo $course[$i] . " " . $section[$i] . " " . $title[$i] . "</br>";
}

?>

</div><!-- end wrapper div -->
</body>
</HTML>