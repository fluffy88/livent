<%@page import="java.io.File"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>File Upload</title>
	</head>
	<body>
		<center>
			<h1>File Upload</h1>
			<form method="post" action="Upload" enctype="multipart/form-data">
				Select file to upload: <input type="file" name="file[]" multiple accept="image/*" /><br />
				<br />
				<input type="submit" value="Upload" />
			</form>
		</center>

		<ul>
		<%
			final File dir = new File("uploads");
			final File[] images = dir.listFiles();
			if (images != null) {
				for (final File image : images) {
					out.println("<li>" + image.getPath() + "</li>");
				}
			}
		%>
		</ul>
	</body>
</html>