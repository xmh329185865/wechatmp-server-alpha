package ana.model.qrcode;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

@SuppressWarnings({"rawtypes","unchecked"})
public class GenerateQRFile {
	
	public void stringToQR(String content, String path, String filename){
		try{
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
  
		    Map hints = new HashMap();
		    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		    BitMatrix bitMatrix = deleteWhite(
		    						multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 320, 320,hints));
		    File file1 = new File(path,filename);

		    MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
		} catch (Exception e){
			e.printStackTrace();
		}
	    
	}

	private BitMatrix deleteWhite(BitMatrix matrix){  
	    int[] rec = matrix.getEnclosingRectangle();  
	    int resWidth = rec[2] + 20;  
	    int resHeight = rec[3] + 20;  
	  
	    rec[0] -= 10;
	    rec[1] -= 10;
	    
	    BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);  
	    resMatrix.clear();  
	    for (int i = 0; i < resWidth; i++) {  
	        for (int j = 0; j < resHeight; j++) {  
	            if (matrix.get(i + rec[0], j + rec[1]))  
	                resMatrix.set(i, j);  
	        }  
	    }  
	    return resMatrix;  
	}  
	
}
