package com.sinse.shopadmin.product.view;

import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JDialog;

import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.util.FileUtil;

public class UploadDialog extends JDialog{
	ProductPage productPage;
	
	public UploadDialog(ProductPage productPage) {
		this.productPage = productPage;
		
		JDialog dialog = new JDialog(this, "업로드 정보", true);
		dialog.setSize(700, 600);
		dialog.setLocationRelativeTo(productPage);
		dialog.setLayout(new FlowLayout());
		
		//커스텀된 바를 임시로 6개 화면에 부착해보자 
		for(int i = 0; i < productPage.files.length; i++) {
			
			File dest = FileUtil.createFile(Config.PRODUCT_IMAGE_PATH, FileUtil.getExt(productPage.files[i].getName()));
			MyBar bar = new MyBar(productPage.files[i], dest);
			Thread thread = new Thread(bar);
			thread.start();
			dialog.add(bar);	
		}
		
		dialog.setVisible(true);
	}
}
