// package metaInformation.example.Extract;

// import java.io.IOException;

// import org.jsoup.Jsoup;
// import org.jsoup.nodes.Document;
// import org.jsoup.nodes.Element;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;


// @RestController
// public class restcontroller {
    

//     @PostMapping("/upload")
//     public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
//         try {
//             if (!file.isEmpty()) {
//                 Document doc = Jsoup.parse(file.getInputStream(), null, "");
//                 StringBuilder metadata = new StringBuilder();
//                 for (Element meta : doc.select("metadata")) {
//                     metadata.append(meta.text()).append("\n");
//                 }
//                 model.addAttribute("metadata", metadata.toString());
//                 return "metadata";
//             } else {
//                 model.addAttribute("message", "Please select a file to upload.");
//                 return "upload";
//             }
//         } catch (IOException e) {
//             model.addAttribute("message", "Failed to upload file: " + e.getMessage());
//             return "upload";
//         }
//     }
// }
