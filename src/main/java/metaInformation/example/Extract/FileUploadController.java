package metaInformation.example.Extract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Controller
public class FileUploadController {





    @RequestMapping("/")
    public String index() {
        return "index";
    }


    private final ResourceLoader resourceLoader;

    public FileUploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            Document doc = Jsoup.parse(file.getInputStream(), "UTF-8", "");
            Element svgElement = doc.select("svg").first();
            String filePath = resourceLoader.getResource("classpath:/templates/output.rtf").getFile().getAbsolutePath();
           
            if (svgElement != null) {
               // System.out.println(svgElement);
                saveAsRTF(svgElement.html(),filePath);

                return filePath;
                // return svgElement.html(); 
                  
            } else {
                return "No SVG content found.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error processing SVG file.";
        }
    }



     private void saveAsRTF(String content, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
             BufferedWriter writer = new BufferedWriter(osw)) {
            // Write RTF header
            writer.write("{\\rtf1\\ansi\\ansicpg1252\\deff0\\nouicompat\\deflang1033");
            writer.newLine();
            // Write SVG content
            writer.write(content);
            // Write RTF footer
            writer.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        System.out.println("RTF content:");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
