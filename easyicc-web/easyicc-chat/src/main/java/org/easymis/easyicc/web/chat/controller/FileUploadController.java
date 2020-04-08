package org.easymis.easyicc.web.chat.controller;

import java.io.IOException;
import java.io.InputStream;

import org.easymis.easyicc.domain.entity.ChatRecord;
import org.easymis.easyicc.domain.entity.ChatRecordDetail;
import org.easymis.easyicc.fastdfs.FastDFSClient;
import org.easymis.easyicc.fastdfs.FastDFSFile;
import org.easymis.easyicc.service.ChatRecordDetailService;
import org.easymis.easyicc.service.ChatRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.swagger.annotations.Api;

@Api(value = "/file", description = "传送文件")
@Controller
@RequestMapping("/file")
public class FileUploadController extends IdentityRepository{
	@Autowired
	private ChatRecordService chatRecordService;
	@Autowired
	private ChatRecordDetailService chatRecordDetailService;
    private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @GetMapping("/upload.html")
    public String upload(Model model,String chatId,String cId,String lang) {
    	model.addAttribute("chatId", chatId);
    	model.addAttribute("cId", cId);
    	model.addAttribute("lang", lang);
        return "file/upload";
    }
    @PostMapping("/upload.do")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,String chatId,String cId,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
/*        try {
            String path=saveFile(file);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("path",
                    "file path url '" + path + "'");
        } catch (Exception e) {
            logger.error("upload file failed",e);
        }*/
        ChatRecord chatRecord=chatRecordService.findById(chatId);
		ChatRecordDetail bean= new ChatRecordDetail();
		bean.setOrgId(chatRecord.getOrgId());
		bean.setChatId(chatId);
		bean.setMessage("http://file.easyliao.com/M00/20/F9/Ch6jw16NRdaEJxfQAAAAAAaI_Ug622.png?ext=.png");
		bean.setFromUserId(chatRecord.getVisitorId());
		bean.setType("RECORD_FILE");
		chatRecordDetailService.save(bean);
        return "/file/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    /**
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath={};
        String fileName=multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream=multipartFile.getInputStream();
        if(inputStream!=null){
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            logger.error("upload file Exception!",e);
        }
        if (fileAbsolutePath==null) {
            logger.error("upload file failed,please upload again!");
        }
        String path=FastDFSClient.getTrackerUrl()+fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
        return path;
    }
}
