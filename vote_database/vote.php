<?php 


	$connect = mysql_connect("localhost","jimin","jimin980908") or die("SQL server 에 연결할 수 없습니다."); 

	mysql_query("SET NAMES UTF8");
	
	mysql_select_db("vote", $connect);


	session_start();

	$sql = "select * from candidate";
	

	$result = mysql_query($sql , $connect);


	$total_record = mysql_num_rows($result);


	echo echo "{\"status\":\"OK\",\"num_results\":\"$total_record\",\"results\":[";

	for ($i = 0; $i < $total_record; $i++)
	{

		mysql.data_seek($result , $i);
		 $row = mysql_fetch_array($result);
   	echo "{\"imgurl\":$row[imgurl],\"txt1\":\"$row[txt1]\",\"txt2\":\"$row[txt2]\"}";

	if ($i<$total_record-1) { 
		echo ",";
	}

	}

	echo "]}";


?>


	


 