<!DOCTYPE html>
<html>
<head>
<title>Welcome to Renobidz</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
body {
 font-family: Verdana, Geneva, sans-serif;
 font-size: 12px;
 color: #464646;
}

p,h1,h2,h3,h4,h5,h6 {
 margin: 0;
 padding: 0;
}

ul {
 margin: 0;
 padding: 0;
 list-style: none;
}

img {
 margin: 0;
 padding: 0;
 border: none;
}

p {
 font-size: 12px;
 margin: 0;
 padding: 15px 0 0 0;
 line-height: 18px;
 text-align: justify;
}

ol {
 margin: 0;
 padding: 5px 0 5px 20px;
 font-size: 12px;
 line-height: 18px;
}

ol li,ul li {
 margin: 0;
 padding: 0;
 line-height: 18px;
 font-size: 12px;
}

ul {
 margin: 0;
 padding: 5px 0 5px 20px;
 list-style: disc;
}

a {
 color: #09F;
 text-decoration: none;
}

a:hover {
 color: #09f;
 text-decoration: underline;
}
</style>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
 marginheight="0">
 <!-- Save for Web Slices (Untitled-1) -->
 <table id="Table_01"
  style="border: solid 1px #AAA; padding: 5px; width: 650px; height: 350px;"
  align="center">
  <tr>
   <td>Renobidz</td>
  </tr>

  <tr>
   <td colspan="3"><div
     style="clear: both; width: 100%; height: 2px; background-color: #999;"></div></td>
  </tr>

  <tr>
   <td colspan="3">
    <p align="justify">Dear ${user.name},</p>
    <p align="justify">Your email <b>${user.email}</b> are now registered with Renobidz.ch</p>
    <br/>
    <p align="justify">Please click on the link below to verify your email with us.</p>
    <p align="justify"><a href="${user.verifyUrl}">${user.verifyUrl}</a></p>
    <br/> 
    <p align="justify">If the above link doesn't work please copy and paste the following url in you browser directly.</p>
    <p align="justify">${user.verifyUrl}</p>
    <br/>

    <p>Thanks &amp; Regards,<br/>
    Team Renobidz</p>
    <p>Click on this url to have a look at our <a href="http://www.renobidz.com/privacy">Privacy Policy</a> </p>
   </td>
  </tr>
 </table>
 <!-- End Save for Web Slices -->
</body>
</html>