package com.me.myapp.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.myapp.pojo.App;

@Controller
public class HomeController {

	@RequestMapping(value="/aboutUs",method=RequestMethod.GET)
	public ModelAndView getAboutUs()
	{
		ModelAndView mv=new ModelAndView();
		ArrayList<String> developers=new ArrayList<String>();
		developers.add("firstDeveloper");
		developers.add("secondDeveloper");
		Date launchDate=new Date();
		Date lastUpdated=new Date();
		ArrayList<Integer> versions=new ArrayList<Integer>();
		versions.add(2);
		versions.add(1);
		App app=new App("Myapp", developers, launchDate, lastUpdated,versions);
		mv.setViewName("aboutUs");
		mv.addObject("app",app);
		
		return mv;
		
	}
	
	@RequestMapping(value="/mapUs",method=RequestMethod.GET)
	public ModelAndView getMapUSPage(HttpServletRequest req)
	{
		ModelAndView mv=new ModelAndView("googlemap");
		return mv;
	}
	private static final String EXTERNAL_FILE_PATH="C:\\WebTools_STS_Projects\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\NinjaApplication\\";
	@RequestMapping(value="/download/{filename}", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, @PathVariable("filename") String filename) throws IOException {
	
		String newFilename=filename.replaceAll("_","\\\\");
		newFilename=newFilename+".pdf";
		File file = null;
		/*String givenfilename=filename;
		String slash="\\";
		String wantedfilename=EXTERNAL_FILE_PATH+"newfile"+slash+"PROJECT DESCRIPTION.pdf";*/
		file = new File(EXTERNAL_FILE_PATH+newFilename);
		
		if(!file.exists()){
			
			String errorMessage = "Sorry. The file you are looking for does not exist";
			System.out.println(errorMessage+" File path: "+file.getPath()+" ----- "+newFilename);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
		}
		System.out.println("File exists");
		
		String mimeType= URLConnection.guessContentTypeFromName(file.getName());
		if(mimeType==null){
			System.out.println("mimetype is not detectable, will take default");
			mimeType = "application/octet-stream";
		}
		
		System.out.println("mimetype : "+mimeType);
		
        response.setContentType(mimeType);
        
        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));

        
        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        
        response.setContentLength((int)file.length());

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
	
	
	@RequestMapping(value="/getImage/{eventname}/{filename}",method=RequestMethod.GET)
	public void getImage(HttpServletRequest request,HttpServletResponse response,@PathVariable("eventname") String eventname,@PathVariable("filename") String filename) throws IOException
	{
		
        //here i uploaded my image in this path
        // You can set any path here
        String imagePath = "C:\\WebTools_STS_Projects\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\NinjaApplication\\";
        imagePath+=""+eventname+"\\"+filename+".jpg";
        

        System.out.println("Retrieving:+"+imagePath);

        response.setContentType("image/jpeg");  
        ServletOutputStream out;  
        out = response.getOutputStream();  
        FileInputStream fin = new FileInputStream(imagePath);  
          
        BufferedInputStream bin = new BufferedInputStream(fin);  
        BufferedOutputStream bout = new BufferedOutputStream(out);  
        int ch =0; ;  
        while((ch=bin.read())!=-1)  
        {  
        bout.write(ch);  
        }  
          
        bin.close();  
        fin.close();  
        bout.close();  
        out.close();  
	}
	
}
