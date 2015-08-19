package com.letsgetmarried;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/Upload")
@MultipartConfig
public class Uploader extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("upload.jsp").forward(request, response);
	}

	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final PrintWriter out = response.getWriter();

		for (final Part part : request.getParts()) {
			final String fileName = extractFileName(part);
			final InputStream is = part.getInputStream();
			final File dir = new File("uploads");
			final File file = new File(dir, fileName);
			dir.mkdir();
			log("Does " + dir.getAbsolutePath() + " exist? " + dir.exists());
			Files.copy(is, file.toPath());
			// part.write(fileName);

			out.printf("{file:\"%s\", upload_path:\"%s\", state:\"success\"}", fileName, file.getAbsolutePath());
		}
	}

	/**
	 * Extracts file name from HTTP header content-disposition
	 */
	private String extractFileName(final Part part) {
		final String contentDisp = part.getHeader("content-disposition");
		final String[] items = contentDisp.split(";");
		for (final String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length()-1);
			}
		}
		return "";
	}
}
