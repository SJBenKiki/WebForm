function validateForm(){
  
  alert("Validation...")

  var valid = true;

  var message = "The following errors have occurred:";
  var fName = document.newUser.fName.value;
  var lName= document.newUser.lName.value;
  var username = document.newUser.username.value;
  var description = document.newUser.username.value;
  var password = document.newUser.password.value;
  var confirm = document.newUser.confirm.value;
  
  var genders = document.getElementsByName("gender");

    
  if(fName == null || fName == ""){
    
    valid = false;
    message += "\rFirst name is missing";

    document.newUser.fName.style.backgroundColor = "Yellow";
  }

  if(lName == null || lName == ""){
    valid = false;
    message +=  "\rLast name is missing";

    document.newUser.lName.style.backgroundColor = "Yellow";
  }

  if(fName == lName){
    valid = false;
    message += "\rFirst name and last name cannot be the same";

    document.newUser.lName.style.backgroundColor= "Yellow";
    document.newUser.fName.style.backgroundColor= "Yellow";
  }

  if(username == "" || username == null){
    valid = false;
    message += "\rNo username has been entered";

    document.newUser.username.style.backgroundColor = "Yellow";
  }

  if(password == null || password == ""){
    valid = false;
    message += "\rPlease enter a password";
  
    document.newUser.password.style.backgroundColor = "Yellow";
  }

  if(confirm == null || confirm == ""){
  valid = false;
  message += "\rPleasee enter password confirmation";

  document.newUser.confirm.style.backgroundColor = "Yellow";
  }

  if(password != confirm){
    valid = false;
    message += "\rPassword does not match";

    document.newUser.password.style.backgroundColor= "Yellow";
    document.newUser.confirm.style.backgroundColor= "Yellow";
  }  

  if(password.length < 5 || confirm.length < 5 || password.length > 10 || confirm.length > 10 ){
    valid = false;
    message += "\rPassword incorrect length";

    document.newUser.password.style.backgroundColor= "Yellow";
    document.newUser.confirm.style.backgroundColor= "Yellow";
  }

  


  if(genders[0].checked != true && genders[1].checked != true){
    valid = false;
    message += "\rNo gender selected";


   }

  if(valid == false){
    alert(message);
  }

  if(valid == false){
    return false;
  }
}