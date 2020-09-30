<?php
if(isset($_POST["action"])) {
	$name = $_POST['name'];                 // Sender's name
	$email = $_POST['email'];     // Sender's email address
	//$phone  = $_POST['phone'];     // Sender's email address
	//$website  = $_POST['website'];     // Sender's website
	$message = $_POST['message'];    // Sender's message
	$from = 'logeswaran.hbs@gmail.com';    
	//$to = 'admin@edutelacademy.com';     // Recipient's email address
	$to = 'logeswaran.hbs@gmail.com';
	$subject = 'Message from Contact Form.';

	//$body = " From: $name \n E-Mail: $email \n Phone : $phone \n Message : $message"  ;
	$body = "From: $name \n";   
  	$body.= "E-Mail: $email \n";
	//$body.= "Phone : $phone \n";  
	//$body.= "Website : $website \n";  
	$body.= "Message : $message \n";
	
	// init error message 
	$errmsg='';
	// Check if name has been entered
	if (!$_POST['name']) {
		//$errmsg = 'Please enter your name';
	}

	
	/* Check required field not blank */
	
	// Check if email has been entered and is valid
	if (!$_POST['email'] || !filter_var($_POST['email'], FILTER_VALIDATE_EMAIL)) {
		//$errmsg = 'Please enter a valid email address';
	}	

	//Check if message has been entered
	if (!$_POST['message']) {
		//$errmsg = 'Please enter your message';
	}
 
	$result='';
	// If there are no errors, send the email
	if (!$errmsg) {
		if (mail ($to, $subject, $body, $from)) {
			$result='<div class="alert alert-success">Thank you for contacting us. Your message has been successfully sent. We will contact you very soon!</div>'; 
		} 
		else {
		  $result='<div class="alert alert-danger">Sorry there was an error sending your message. Please try again later.</div>';
		}
	}
	//else{
		//$result='<div class="alert alert-danger">'.$errmsg.'</div>';
	//}
		//echo $result;
		
		 //Email response
 		header("location: ../academia/thankyou.html");
	}
?>
