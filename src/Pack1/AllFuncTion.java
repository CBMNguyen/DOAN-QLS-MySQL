package Pack1;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.util.Formatter;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Scanner;
//import com.mysql.cj.jdbc.CallableStatement;

public class AllFuncTion {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement Ptmt;
	private java.sql.CallableStatement cStmt;
	private ResultSet rs;
	
	//  Function connect with My SQL
		
	public void ConnectDB() {
		try {
			
			String name,pass,url;
			url="jdbc:mysql://localhost:3306/qlsach";  
            name="root";  
            pass="sql2012";
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, name, pass);
			//System.out.println("Noi Ket Thanh Cong!");
			
		}catch(Exception ex) {
			System.out.println("Noi Ket That Bai!");
			ex.printStackTrace();
		}
	}
	int num = 0;
	
	//==========================================================================================
	// Function Show Menu for project
	
	public void ShowMenu() {
		if(num == 0)
			System.out.println("\n               <=======================================     CHUONG TRINH UNG DUNG QUAN LI SACH     =======================================>\n\n");
		System.out.println("                                                         +--------------------------------------+");
		System.out.println("                                                         | [1] Hien Thi Thong Tin Nhan Vien.    |");
		System.out.println("                                                         | [2] Hien Thi Thong Tin Sach.         |");
		System.out.println("                                                         | [3] Hien Thi Hoa Don Da Ban.         |");
		System.out.println("                                                         | [4] Tim Sach.                        |");
		System.out.println("                                                         | [5] Them Sach Moi.                   |");
		System.out.println("                                                         | [6] Cap Nhat Sach.                   |");
		System.out.println("                                                         | [7] Tim Nhan Vien.                   |");
		System.out.println("                                                         | [8] Them Nhan Vien Moi.              |");
		System.out.println("                                                         | [9] Cap Nhat Nhan Vien.              |");
		System.out.println("                                                         | [10] Tim Hoa Don.                    |");
		System.out.println("                                                         | [11] Lap Hoa Don.                    |");
		System.out.println("                                                         | [12] Xoa Hoa Don.                    |");
		System.out.println("                                                         | [13] Thong Ke.                       |");
		System.out.println("                                                         | [14] Thoat.                          |");
		System.out.println("                                                         +--------------------------------------+");
		Scanner kb = new Scanner(System.in);
		System.out.print("                                                         --> ");
		
	
		try {
			int pick = kb.nextInt();
		
		num++;
		
		switch(pick) {
		case 1: getInforNV(); break;
		case 2: getInforBook(); break;
		case 3: showHD(); break;
		case 4: FindBook(); break;
		case 5: AddNewBook(); break;
		case 6: updateBook(); break;
		case 7: FindNV(); break;
		case 8: AddEmployee(); break;
		case 9: updateEmployee(); break;
		case 10: FindHD(); break;
		case 11: LapHD(); break;
		case 12: XoaHD(); break;
		case 13: ThongKe(); break;
		case 14: break;
		default:{
			System.out.println("                                                         Nhap Sai Roi Nhap Lai Di!!!");
			ShowMenu();
		}
	}
		}catch(Exception e) {
//			System.out.println(e);
		}
	}
	
	//=================================================================================================
	// Function show information of Employees
	
	public void getInforNV() {
		Scanner kb = new Scanner(System.in);
		int maxTen = 0;
		int maxDiaChi = 0;
		int maxEmail = 0;
		int maxChucVu = 0;
		int count = 0;
		String loai = "";
		try {
			this.ConnectDB();
			
			cStmt = conn.prepareCall("{call getInForNV()}");
			rs = cStmt.executeQuery();
			
			while(rs.next()) {
				
				String tennv = rs.getString("ten_nv");
				String diachi = rs.getString("diachi");
				String email = rs.getString("email");
				String chucvu = rs.getString("chuc_vu");
				
				if(tennv.length() > maxTen)
					maxTen = tennv.length();
				if(diachi.length() > maxDiaChi)
					maxDiaChi = diachi.length();
				if(email.length() > maxEmail)
					maxEmail = email.length();
				if(chucvu.length() > maxChucVu)
					maxChucVu = chucvu.length();
			}
			
			cStmt = conn.prepareCall("{call getInForNV()}");
			rs = cStmt.executeQuery();
			
			System.out.format("+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n");
			System.out.format("| Ma_NV         Ten_NV            GT    Ngay Sinh                      Dia Chi                                Email                         SDT        Chuc Vu    Luong CB    Phu Cap |\n");
			System.out.format("+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n");
			while(rs.next()) {
				
				String ma_nv = rs.getString("ma_nv");
				String ten_nv = rs.getString("ten_nv");
				String gioitinh = rs.getString("gioitinh");
				String ngay_lam_viec = rs.getString("ngay_lam_viec");
				String diachi = rs.getString("diachi");
				String email = rs.getString("email");
				String sdt = rs.getString("sdt");
				String chuc_vu = rs.getString("chuc_vu");
				String luong_cb = rs.getString("luong_cb");
				String phucap = rs.getString("phu_cap");
				
				for(int i = ma_nv.length(); i <= 5; i++)
					ma_nv += " ";
				
				for(int i = ten_nv.length(); i < maxTen; i++)
					ten_nv += " ";
				
				for(int j = diachi.length(); j < maxDiaChi; j++)
					diachi += " ";
				
				for(int k = email.length(); k < maxEmail; k++)
					email += " ";
				
				for(int l = chuc_vu.length(); l < maxChucVu; l++)
					chuc_vu += " ";
				
				System.out.format("| %s    %s    %s    %s    %s    %s    %s    %s    %s       %s  |\n", ma_nv, ten_nv, gioitinh,ngay_lam_viec, diachi, email, sdt, chuc_vu, luong_cb, phucap);
			}
			System.out.format("+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n");
			System.out.println("   0. Thoat     1. Quay Lai");
			System.out.print("-> ");
			
			int pick = kb.nextInt();
			switch(pick) {
				case 1: ShowMenu();break;
				default: break;
			}
			
		}catch(Exception ex){
			System.out.println("SQLException: "+ ex.getMessage());
		}
		finally {
			if(cStmt != null) {
				try {
					cStmt.close();
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: "+ ex.getMessage());
				}
			}
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: "+ ex.getMessage());
				}
				cStmt = null;
			}
		}
	}
	
	//========================================================================================
	// Function Add Employee
	
	public void AddEmployee() {
		Scanner kb = new Scanner(System.in);
		try {
			this.ConnectDB();
			
			System.out.print("                                                         Nhap vao ma nhan vien: "); String manv = kb.nextLine();
			System.out.print("                                                         Nhap vao ten nhan vien: "); String tennv = kb.nextLine();
			System.out.print("                                                         Nhap vao gioi tinh: "); Byte gt = kb.nextByte(); kb.nextLine();
			System.out.print("                                                         Nhap vao ngay sinh: "); String ngaysinh = kb.nextLine();
			System.out.print("                                                         Nhap vao ngay lam viec: "); String ngaylam = kb.nextLine();
			System.out.print("                                                         Nhap vao diachi: "); String diachi = kb.nextLine();
			System.out.print("                                                         Nhap vao email: "); String email = kb.nextLine();
			System.out.print("                                                         Nhap vao so dien thoai: "); String sdt = kb.nextLine();
			System.out.print("                                                         Nhap vao chucvu: "); String chucvu = kb.nextLine();
			System.out.print("                                                         Nhap vao luong can ban: "); double luong = kb.nextDouble(); kb.nextLine();
			System.out.print("                                                         Nhap vao phu cap: "); double phucap = kb.nextDouble(); kb.nextLine();
			
			cStmt = conn.prepareCall("{call getInForNV()}");
			rs = cStmt.executeQuery();
			
			String MaNV[] = new String[50];
			int i = 0;
		
			while(rs.next()) {
				String ma_nv = rs.getString("ma_nv");
			
				if(ma_nv.equals(manv))
					{i = 1; break;}
			}
			
			cStmt = conn.prepareCall("{call AddEmployee(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			cStmt.setString(1, manv);
			cStmt.setString(2, tennv);
			cStmt.setByte(3, gt);
			cStmt.setString(4, ngaysinh);
			cStmt.setString(5, ngaylam);
			cStmt.setString(6, diachi);
			cStmt.setString(7, email);
			cStmt.setString(8, sdt);
			cStmt.setString(9, chucvu);
			cStmt.setDouble(10, luong);
			cStmt.setDouble(11, phucap);
			
			Date ns = new SimpleDateFormat("dd/MM/yyyy").parse(ngaysinh);
			Date nl = new SimpleDateFormat("dd/MM/yyyy").parse(ngaylam);
			
			if(i == 1) {
				System.out.println("                                                         Nhan Vien Da Ton Tai!");
			}else if(ns.after(nl) || ns.equals(nl)) {
				System.out.println("                                                         Ngay Sinh Hoac Ngay Lam Khong Hop Le!!");
			}
			else if(sdt.length() > 10) {
				System.out.println("                                                         So Dien Thoai Khong Hop Le!");
			}else if(luong <= 0) {
				System.out.println("                                                         Luong Khong Hop Le!");
			}else if(phucap < 0) {
				System.out.println("                                                         Phu Cap Khong Hop Le!");
			}
			else{
				cStmt.executeUpdate();
				System.out.println("                                                         Them Nhan Vien Thanh Cong.");
			}
			
			System.out.println("                                                         0. Thoat     1. Quay Lai Menu");
			System.out.print("                                                         -> ");
			int pick = kb.nextInt();
			switch(pick) {
				case 1: ShowMenu();break;
				default: break;
			}
			
		}catch(Exception ex){
			System.out.println("SQLException: "+ ex.getMessage());
		}
		finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: "+ ex.getMessage());
				}
			}
			if(cStmt != null) {
				try {
					cStmt.close();
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: "+ ex.getMessage());
				}
			}
		}
	}
	
	//========================================================================================
	// Function Update Employee
	
	public void updateEmployee() {
		Scanner kb = new Scanner(System.in);
		try {
			this.ConnectDB();
				
			System.out.println("                                                         <1> Cap Nhat Chuc Vu.");
			System.out.println("                                                         <2> Cap Nhat Luong.");
			System.out.println("                                                         <3> Cap Nhat Phu Cap.");
			System.out.println("                                                         <4> Xoa Nhan Vien.");
			System.out.print("> ");
			
			int select = kb.nextInt();
			kb.nextLine();
			
			switch(select) {
				case 1:{
					System.out.print("                                                         Nhap vao ma nhan vien: ");
					String mnv = kb.nextLine();
					System.out.print("                                                         Nhap vao chuc vu: ");
					String cv = kb.nextLine();
					
					cStmt = conn.prepareCall("{call UpdateOrder(?, ?)}");
					
					cStmt.setString(1, mnv);
					cStmt.setString(2, cv);
					
					System.out.println("                                                         Update Successful.");
					cStmt.executeUpdate();
					
					break;
				}
				case 2:{
					System.out.print("                                                         Nhap vao ma nhan vien: ");
					String mnv = kb.nextLine();
					System.out.print("                                                         Nhap vao luong: ");
					double luong = kb.nextDouble();
					kb.nextLine();
					
					cStmt = conn.prepareCall("{call UpdateSalary(?, ?)}");
					cStmt.setString(1,mnv);
					cStmt.setDouble(2, luong);
					
					if(luong <= 0) {
						System.out.println("                                                         Luong Khong Hop Le!");
					}else{
						System.out.println("                                                         Update Successful.");
						cStmt.executeUpdate();
					}
					
					break;
				}
				case 3:{
					System.out.print("                                                         Nhap vao ma nhan vien: ");
					String mnv = kb.nextLine();
					System.out.print("                                                         Nhap vao phu cap: ");
					double pc = kb.nextDouble();
					kb.nextLine();
					cStmt = conn.prepareCall("{call UpdateGrants(?, ?)}");
					cStmt.setString(1, mnv);
					cStmt.setDouble(2, pc);
								
					if(pc < 0) {
						System.out.println("                                                         Phu Cap Khong Hop Le!");
					}else{
						System.out.println("                                                         Update Successful.");
						cStmt.executeUpdate();
					}
					
					break;
				}
				case 4:{
					System.out.print("                                                         Nhap vao ma nhan vien: ");
					String mnv = kb.nextLine();
					
					String MaNV[] = new String[50];
					int i = 0;
					
					cStmt = conn.prepareCall("{call getInForNV()}");
					rs = cStmt.executeQuery();
					
					while(rs.next()) {
						String ma_nv = rs.getString("ma_nv");
						if(ma_nv.equals(mnv))
							{i = 1; break;}
					}
					
					cStmt = conn.prepareCall("{call DeleteEmployee(?)}");
					cStmt.setString(1, mnv);
								
					if(i == 0) {
						System.out.println("                                                         Nhan Vien Khong Ton Tai!");
					}else{
						System.out.println("                                                         Update Successful.");
						cStmt.executeUpdate();
					}
					
					break;
				}
				default:{
					System.out.println("                                                         Nhap sai roi nhap lai di!");
					updateEmployee();
				}
			}
			
			cStmt = conn.prepareCall("{call AddBook(?, ?, ?, ?, ?, ?, ?)}");
			
			System.out.println("                                                         0. Thoat     1. Quay Lai Menu");
			System.out.print("                                                         -> ");
			int pick = kb.nextInt();
			switch(pick) {
				case 1: ShowMenu();break;
				default: break;
			}
			
		}catch(Exception ex){
			System.out.println("SQLException: "+ ex.getMessage());
		}
		finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: "+ ex.getMessage());
				}
				cStmt = null;
			}
			if(cStmt != null) {
				try {
					cStmt.close();
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: "+ ex.getMessage());
				}
				cStmt = null;
			}
		}
	}
	
	//========================================================================================
	// Function show Books information 
	
public void getInforBook() {
	Scanner kb = new Scanner(System.in);
	int maxTenSach = 0;
	int maxGia = 0;
	int maxNhom = 0;
	int maxSL = 0;
	try {
		this.ConnectDB();
		
		cStmt = conn.prepareCall("{ call getInForBook()}");
			rs = cStmt.executeQuery();
		
		while(rs.next()) {
			String tensach = rs.getString("ten_sach");
			String dongia = rs.getString("don_gia");
			String manhom = rs.getString("ma_nhom");
			String sl_ton = rs.getString("sl_ton");
			if(tensach.length() > maxTenSach)
				maxTenSach = tensach.length();
			if(dongia.length() > maxGia)
				maxGia = dongia.length();
			if(manhom.length() > maxNhom)
				maxNhom = manhom.length();
			if(sl_ton.length() > maxSL)
				maxSL = sl_ton.length();
		}
		
		cStmt = conn.prepareCall("{call getInForBook()}");
		rs = cStmt.executeQuery();
		
		System.out.format("                         +---------------------------------------------------------------------------------------------------+\n");
		System.out.format("                         |Ma Sach      Ten Sach                                   Don Gia    SL_Ton Ma Nhom   Ma TG   Ma NCC |\n");
		System.out.format("                         +---------------------------------------------------------------------------------------------------+\n");
		while(rs.next()) {
			
			String masach = rs.getString("ma_sach");
			String tensach = rs.getString("ten_sach");
			String dongia = rs.getString("don_gia");
			String sl_ton = rs.getString("sl_ton");
			String manhom = rs.getString("ma_nhom");
			String matg = rs.getString("ma_tg");
			String mancc = rs.getString("ma_ncc");
			
			for(int i = tensach.length(); i < maxTenSach; i++) {
				tensach += " ";
			}
			
			for(int j = dongia.length(); j < maxGia; j++) {
				dongia += " ";
			}
			
			for(int k = manhom.length(); k < maxNhom; k++) {
				manhom += " ";
			}
			
			for(int e = sl_ton.length(); e < maxSL; e++) {
				sl_ton += " ";
			}
			
			System.out.format("                         | %s    %s    %s    %s    %s    %s    %s |\n", masach, tensach, dongia,sl_ton, manhom, matg, mancc);
		}
		System.out.format("                         +---------------------------------------------------------------------------------------------------+\n");
		System.out.println("                            0. Thoat     1. Quay Lai Menu");
		System.out.print("                         -> ");
		
		int pick = kb.nextInt();
		kb.nextLine();
		switch(pick) {
			case 1: ShowMenu();break;
			default: break;
		}
		
	}catch(Exception ex){
		System.out.println("SQLException: "+ ex.getMessage());
	}finally {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
		}
		if(cStmt != null) {
			try {
				cStmt.close();
				stmt.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
		}
	}
}

//=========================================Add New Book ===========================================

public void AddNewBook() {
	Scanner kb = new Scanner(System.in);
	int sl = 0;
	int slt;

	try {
		this.ConnectDB();
		
		cStmt = conn.prepareCall("{ call getInForBook()}");
		rs = cStmt.executeQuery();
	
		while(rs.next()) {
			sl++;
		}
		
		System.out.print("                                                         Nhap vao ma sach: "); String masach = kb.nextLine();
		System.out.print("                                                         Nhap vao ten sach: "); String tensach = kb.nextLine();
		System.out.print("                                                         Nhap vao don gia: ");double dongia = kb.nextDouble(); kb.nextLine();
		System.out.print("                                                         Nhap vao so luong: "); int soluong = kb.nextInt(); kb.nextLine();
		System.out.print("                                                         Nhap vao ma nhom sach: "); String manhom = kb.nextLine();
		System.out.print("                                                         Nhap vao ma tac gia: "); String matg = kb.nextLine();
		System.out.print("                                                         Nhap vao ma nha cung cap: "); String mancc = kb.nextLine();
		
		slt = Integer.parseInt(masach.substring(1));
		
		cStmt = conn.prepareCall("{call AddBook(?, ?, ?, ?, ?, ?, ?)}");
		cStmt.setString(1, masach);
		cStmt.setString(2, tensach);
		cStmt.setDouble(3, dongia);
		cStmt.setInt(4, soluong);
		cStmt.setString(5, manhom);
		cStmt.setString(6, matg);
		cStmt.setString(7, mancc);
	
		if(slt <= sl) {
			System.out.println("                                                         Sach Da Ton Tai!");
		}else if(soluong <= 0 || dongia <= 0) {
			System.out.println("                                                         So Luong Hoac Don Gia Khong Hop Le!");
		}
		else{
			cStmt.executeUpdate();
			System.out.println("                                                         Them Sach Thanh Cong.");
		}
		
		System.out.println("                                                         0. Thoat     1. Quay Lai Menu");
		System.out.print("                                                         -> ");
		int pick = kb.nextInt();
		kb.hasNextLine();
		switch(pick) {
			case 1: ShowMenu();break;
			default: break;
		}
		
	}catch(Exception ex){
		System.out.println("SQLException: "+ ex.getMessage());
	}
	finally {
		if(rs != null) {
			try {
				stmt.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
			cStmt = null;
		}
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
		}
		if(cStmt != null) {
			try {
				cStmt.close();
				rs.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
		}
	}
}

//==================================================================================
// =========================Update Book=======================

public void updateBook() {
	Scanner kb = new Scanner(System.in);
	int sll = 0;
	try {
		this.ConnectDB();
		
		cStmt = conn.prepareCall("{ call getInForBook()}");
		rs = cStmt.executeQuery();
	
		while(rs.next()) {
			sll++;
		}
		
			
		System.out.println("                                                         <1> Cap Nhat So Luong Sach.");
		System.out.println("                                                         <2> Cap Nhat Don Gia.");
		System.out.println("                                                         <3> Xoa Sach.");
		System.out.print("                                                         > ");
		
		int select = kb.nextInt();
		kb.nextLine();
		
		switch(select) {
			case 1:{
				System.out.print("                                                         Nhap vao ma sach: ");
				String ms = kb.nextLine();
				System.out.print("                                                         Nhap vao so luong: ");
				int sl = kb.nextInt();
				kb.nextLine();
				
				int slms = Integer.parseInt(ms.substring(1));
				
				cStmt = conn.prepareCall("{call UpdateAmountBook(?, ?)}");
				
				cStmt.setString(1, ms);
				cStmt.setInt(2, sl);
				
				if(slms > sll) {
					System.out.println("                                                         Sach Khong Ton Tai!");
				}else if(sl < 0){
					System.out.println("                                                         So Luong Khong Hop Le!");
				}
				else{
					System.out.println("                                                         Update Successful.");
					cStmt.executeUpdate();
				}
				
				break;
			}
			case 2:{
				System.out.print("                                                         Nhap vao ma sach: ");
				String ms = kb.nextLine();
				System.out.print("                                                         Nhap vao Don Gia: ");
				double dongia = kb.nextDouble();
				kb.nextLine();
				
				int slms = Integer.parseInt(ms.substring(1));
				
				cStmt = conn.prepareCall("{call UpdatePriceBook(?, ?)}");
				cStmt.setString(1,ms);
				cStmt.setDouble(2, dongia);
				
				if(slms > sll) {
					System.out.println("                                                         Sach Khong Ton Tai!");
				}else if(dongia <= 0){
					System.out.println("                                                         Don Gia Khong Hop Le!");
				}
				else{
					System.out.println("                                                         Update Successful.");
					cStmt.executeUpdate();
				}
				
				break;
			}
			case 3:{
				System.out.println("                                                         Nhap vao ma sach: ");
				String ms = kb.nextLine();
				cStmt = conn.prepareCall("{call deleteBook(?)}");
				cStmt.setString(1, ms);
				
				int slms = Integer.parseInt(ms.substring(1));
				
				if(slms > sll) {
					System.out.println("                                                         Sach Khong Ton Tai!");
				}else{
					System.out.println("                                                         Update Successful.");
					cStmt.executeUpdate();
				}
				
				break;
			}
			default:{
				System.out.println("                                                         Nhap sai roi nhap lai di!");
				updateBook();
			}
		}
		
		cStmt = conn.prepareCall("{call AddBook(?, ?, ?, ?, ?, ?, ?)}");
		
		System.out.println("                                                         0. Thoat     1. Quay Lai Menu");
		System.out.print("                                                         -> ");
		int pick = kb.nextInt();
		switch(pick) {
			case 1: ShowMenu();break;
			default: break;
		}
		
	}catch(Exception ex){
		System.out.println("SQLException: "+ ex.getMessage());
	}
	finally {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
		}
		if(cStmt != null) {
			try {
				cStmt.close();
				stmt.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
		}
	}
}


//==================================================================================
//	Funtion show all invoices

public void showHD() {
	Scanner kb = new Scanner(System.in);
	int maxSttHd = 0;
	int maxNgayBan = 0;
	int maxSSB = 0;
	int maxTongTIen = 0;
	int sl = 0;
	int T_tien = 0;
	String so_luong = "";
	String sumMoney = "";
	
	try {
		this.ConnectDB();
		
		cStmt = conn.prepareCall("{ call ShowHD()}");
			rs = cStmt.executeQuery();
		
		while(rs.next()) {
			String stt_hd = rs.getString("stt_hd");
			String ngay_ban = rs.getString("ngay_ban");
			String ssb = rs.getString("so_sach_ban");
			String tong_tien = rs.getString("Tong_Tien");
			
			sl++;
		
			T_tien += Float.parseFloat(tong_tien);
			
			if(stt_hd.length() > maxSttHd)
				maxSttHd = stt_hd.length();
			if(ngay_ban.length() > maxNgayBan)
				maxNgayBan = ngay_ban.length();
			if(ssb.length() > maxSSB)
				maxSSB = ssb.length();
			if(tong_tien.length() > maxTongTIen)
				maxTongTIen = tong_tien.length();
		}
		
		cStmt = conn.prepareCall("{call ShowHD()}");
		rs = cStmt.executeQuery();
		
		System.out.format("                                                   +-------------------------------------------------+\n");
		System.out.format("                                                   | STT_HD    Ngay_Ban      so_luong    Thanh_Tien  |\n");
		System.out.format("                                                   +-------------------------------------------------+\n");
		while(rs.next()) {
			
			String stt_hd = rs.getString("stt_hd");
			String ngay_ban = rs.getString("ngay_ban");
			String ssb = rs.getString("so_sach_ban");
			String tong_tien = rs.getString("Tong_Tien");
			so_luong = String.valueOf(sl);
			sumMoney = String.valueOf(T_tien);
			
			for(int i = stt_hd.length(); i < maxSttHd; i++) {
				stt_hd += " ";
			}
			
			for(int j = ngay_ban.length(); j < maxNgayBan; j++) {
				ngay_ban += " ";
			}
			
			for(int k = ssb.length(); k < maxSSB; k++) {
				ssb += " ";
			}
			
			for(int e = tong_tien.length(); e < maxTongTIen; e++) {
				tong_tien += " ";
			}
			
			for(int e = so_luong.length(); e < 2; e++) {
				so_luong += " ";
			}
			
			for(int e = sumMoney.length(); e < 8; e++) {
				sumMoney += " ";
			}
			
			System.out.format("                                                   | %s    %s        %s          %s  |\n", stt_hd, ngay_ban, ssb, tong_tien);
		}
		
		System.out.format("                                                   | So Luong: %s                Tong Tien: %s |\n", so_luong, sumMoney);
		System.out.format("                                                   +-------------------------------------------------+\n");
		System.out.println("                                                    0. Thoat     1. Quay Lai Menu");
		System.out.print("                                                 -> ");
		
		int pick = kb.nextInt();
		kb.nextLine();
		switch(pick) {
			case 1: ShowMenu();break;
			default: break;
		}
		
	}catch(Exception ex){
		System.out.println("SQLException: "+ ex.getMessage());
	}finally {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
		}
		if(cStmt != null) {
			try {
				cStmt.close();
				rs.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
		}
	}
}

//============================================================================================
//	Book search Function

public void FindBook() {
	Scanner kb = new Scanner(System.in);
    int DK;
    int count = 0;
    int maxSach = 0;
    int maxSl = 0;
    int u =1;
    String nhom = "";
    String ten = ""; 
    System.out.println("                                                0. Tim Sach Theo Ten Nhom Sach.     1. Tim Sach Theo Ten Sach.");
    System.out.print("                                                -> ");
    while(u == 1) {
    	DK = kb.nextInt();
        kb.nextLine();
    	switch(DK) {
    	case 0: System.out.print("                                                Nhap Ten Nhom Sach: "); nhom = kb.nextLine(); u = 0; break;
    	case 1: System.out.print("                                                Nhap Ten Sach: "); ten = kb.nextLine(); u = 0; break;
    	default: {
    			System.out.print("                                                Nhap Sai Roi Nhap Lai Di!!\n");
    			FindBook();
    	}
    	}
    }

	try {
		this.ConnectDB();
		
		if(nhom.length() > 0) {
			cStmt = conn.prepareCall("{ call FindBookGroup(?)}");
			cStmt.setString(1, nhom);
		}
		else {
			cStmt = conn.prepareCall("{ call FindBookName(?)}");
			cStmt.setString(1, ten);
		}
		
		rs = cStmt.executeQuery();
		
		
		while(rs.next()) {
			String tensach = rs.getString("ten_sach");
			String sl_ton = rs.getString("sl_ton");
			if(tensach.length() > maxSach)
				maxSach = tensach.length();
			
			if(sl_ton.length() > maxSl)
				maxSl = sl_ton.length();
		}
		
		if(nhom.length() > 0) {
			cStmt = conn.prepareCall("{ call FindBookGroup(?)}");
			cStmt.setString(1, nhom);
		}
		else {
			cStmt = conn.prepareCall("{ call FindBookName(?)}");
			cStmt.setString(1, ten);
		}
		
		rs = cStmt.executeQuery();
		
		System.out.println();
		System.out.println("                          +-------------------------------------------------------------------------------------------------------------------------------------------------+");
		System.out.println("                          | Ma Sach             Ten Sach                                          Don Gia         SL Ton        Ma Nhom         Tac Gia        Nha Cung Cap |");
		System.out.println("                          +-------------------------------------------------------------------------------------------------------------------------------------------------+");
		while(rs.next()) {
			String masach = rs.getString("ma_sach");
			String tensach = rs.getString("ten_sach");
			String dongia = rs.getString("don_gia");
			String sl_ton = rs.getString("sl_ton");
			String ma_nhom = rs.getString("ma_nhom");
			String tacgia = rs.getString("ma_tg");
			String ncc = rs.getString("ma_ncc");
			
			for(int i = tensach.length(); i < 43; i++) {
				tensach += " ";
			}
			
			for(int i = ma_nhom.length(); i < 6; i++) {
				ma_nhom += " ";
			}
			
			for(int j = sl_ton.length(); j < maxSl; j++) {
				tensach += " ";
			}
			
			System.out.format("                          | %s              %s         %s           %s           %s           %s             %s   |\n", masach, tensach, dongia,sl_ton, ma_nhom, tacgia, ncc);
			count++;
		}
		
		System.out.println("                          +-------------------------------------------------------------------------------------------------------------------------------------------------+");
		if(count == 0) System.out.println("                                                Sach Khong Ton Tai.");
		
		System.out.println("\n                                                0. Thoat     1. Quay Lai Menu");
		System.out.print("                                                -> ");
		int pick = kb.nextInt();
		switch(pick) {
			case 1: ShowMenu();break;
			default: break;
		}
	}catch(Exception ex){
		System.out.println("SQLException: "+ ex.getMessage());
	}finally {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
		}
		if(cStmt != null) {
			try {
				cStmt.close();
				rs.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
		}
	}
}
//=================================================================================================
//	Employee search function

public void FindNV() {
	Scanner kb = new Scanner(System.in);
    int DK;
    int count = 0;
    int maxTen = 0;
    int maxDiaChi = 0;
    int maxEmail = 0;
    int maxChucVu = 0;
    int u =1;
    String loai = "";
    System.out.println("                                        0. Tim Nhan Vien Theo Ten Nhan Vien.     1. Tim Nhan Vien Theo Chuc Vu.");
    System.out.print("                                      --> ");
    while(u == 1) {
    	DK = kb.nextInt();
        kb.nextLine();
    	switch(DK) {
    	case 0: System.out.print("                                        Nhap Ten Nhan Vien: "); loai = kb.nextLine(); u = 0; break;
    	case 1: System.out.print("                                        Nhap Ten Chuc Vu: "); loai = kb.nextLine(); u = 0; break;
    	default: {
    			System.out.print("Nhap Sai Roi Nhap Lai Di!!");
    	}
    	}
    }

	try {
		this.ConnectDB();
		stmt = conn.createStatement();
		if(stmt.execute("SELECT a.* FROM nhanvien a WHERE (a.ten_nv = "+"'"+loai+"'"+"or a.chuc_vu = "+"'"+loai+"')"))
			rs = stmt.getResultSet();
		
		while(rs.next()) {
			
			String tennv = rs.getString("ten_nv");
			String diachi = rs.getString("diachi");
			String email = rs.getString("email");
			String chucvu = rs.getString("chuc_vu");
			
			if(tennv.length() > maxTen)
				maxTen = tennv.length();
			if(diachi.length() > maxDiaChi)
				maxDiaChi = diachi.length();
			if(email.length() > maxEmail)
				maxEmail = email.length();
			if(chucvu.length() > maxChucVu)
				maxChucVu = chucvu.length();
		}
		
		if(stmt.execute("SELECT a.* FROM nhanvien a WHERE (a.ten_nv = "+"'"+loai+"'"+"or a.chuc_vu = "+"'"+loai+"')"))
			rs = stmt.getResultSet();
		
		System.out.format("+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n");
		System.out.format("| Ma_NV         Ten_NV            GT    Ngay Sinh                      Dia Chi                                Email                         SDT        Chuc Vu    Luong CB    Phu Cap |\n");
		System.out.format("+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n");
	 
		while(rs.next()) {
			String manv = rs.getString("ma_nv");
			String tennv = rs.getString("ten_nv");
			String gioitinh = rs.getString("gioitinh");
			String ngaysinh = rs.getString("ngaysinh");
			String ngay_lam_viec = rs.getString("ngay_lam_viec");
			String diachi = rs.getString("diachi");
			String email = rs.getString("email");
			String sdt = rs.getString("sdt");
			String chucvu = rs.getString("chuc_vu");
			String luongcb = rs.getString("luong_cb");
			String phucap = rs.getString("phu_cap");
			
			for(int i = tennv.length(); i < 19; i++)
				tennv += " ";
			
			for(int j = diachi.length(); j < 45; j++)
				diachi += " ";
			
			for(int k = email.length(); k < 31; k++)
				email += " ";
			
			for(int l = chucvu.length(); l < 9; l++)
				chucvu += " ";
				System.out.format("| %s    %s    %s    %s    %s    %s    %s    %s    %s       %s   |\n", manv, tennv, gioitinh,ngay_lam_viec, diachi, email, sdt, chucvu, luongcb, phucap);
			
			count++;
		}
		System.out.format("+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n");
		
		if(count == 0) System.out.println("                          Nhan Vien Khong Ton Tai.");
		
		System.out.println("                                         0. Thoat     1. Quay Lai Menu");
		System.out.print("                                       --> ");
		int pick = kb.nextInt();
		
		switch(pick) {
			case 1: ShowMenu();break;
			default: break;
		}
	}catch(Exception ex){
		System.out.println("SQLException: "+ ex.getMessage());
	}finally {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
		}
		if(cStmt != null) {
			try {
				cStmt.close();
				rs.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
		}
	}
}

//========================================================================================================
//	Invoice search function

public void FindHD() {
	Scanner kb = new Scanner(System.in);
    int DK;
    int pickNgay = 0;
    int count = 0;
    int u =1;
    int maxTenSach = 0;
    int maxGia = 0;
    int maxThanhTien = 0;
    Double TongTien = 0.0;
    String stt_hd = "";
    String ngay = "";
    System.out.println("                                               0. Tim Hoa Don Theo STT Hoa Don.     1. Tim Hoa Don Theo Ngay.");
    while(u == 1) {
    	System.out.print("                                           --> ");
    	DK = kb.nextInt();
        kb.nextLine();
    	switch(DK) {
    	case 0: System.out.print("                                               Nhap STT Hoa Don: "); stt_hd = kb.nextLine(); ngay = "1000-1-1"; u = 0; break;
    	case 1: System.out.print("                                               Nhap Ngay: "); ngay = kb.nextLine(); u = 0; pickNgay = 1; break;
    	default: {
    			System.out.print("                                               Nhap Sai Roi Nhap Lai Di!!");
    	}
    	}
    }

	try {
		this.ConnectDB();
		stmt = conn.createStatement();
		if(stmt.execute("SELECT * FROM hoadon a, chitiethoadon b, nhanvien c, danhmucsach d WHERE a.ma_nv = c.ma_nv and b.ma_sach = d.ma_sach and a.stt_hd = b.stt_hd and (a.stt_hd = "+"'"+stt_hd+"'"+"or a.ngay_ban = "+"'"+ngay+"')"));
			rs = stmt.getResultSet();
		
			while(rs.next()) {
				String tensach = rs.getString("ten_sach");
				String giaban = rs.getString("giaban");
				String thanhtien = rs.getString("thanh_tien");
				
				if(tensach.length() > maxTenSach)
					maxTenSach = tensach.length();
				if(giaban.length() > maxGia)
					maxGia = giaban.length();
				if(thanhtien.length() > maxThanhTien)
					maxThanhTien = thanhtien.length();
			}
			
			if(stmt.execute("SELECT * FROM hoadon a, chitiethoadon b, nhanvien c, danhmucsach d WHERE a.ma_nv = c.ma_nv and b.ma_sach = d.ma_sach and a.stt_hd = b.stt_hd and (a.stt_hd = "+"'"+stt_hd+"'"+"or a.ngay_ban = "+"'"+ngay+"')"));
			rs = stmt.getResultSet();
			System.out.println("                       +-------------------------------------------------------------------------------------------------------------------------------+");
			System.out.println("                       | STTHD          Ngay Ban         Ten Sach                                       Gia Ban     So Luong   Giam Gia    Thanh Tien  |");
			System.out.println("                       +-------------------------------------------------------------------------------------------------------------------------------+");
		while(rs.next()) {
			String sttHd = rs.getString("stt_hd");
			String tennv = rs.getString("ten_nv");
			String ngayban = rs.getString("ngay_ban");
			String tensach = rs.getString("ten_sach");
			String giaban = rs.getString("giaban");
			String soluong = rs.getString("soluong");
			String giamgia = rs.getString("giam_gia");
			String thanhtien = rs.getString("thanh_tien");
			TongTien = rs.getDouble("tong_tien");
			
			for(int j = tensach.length(); j < 45; j++)
				tensach += " ";
			
			for(int e = giaban.length(); e < 9; e++)
				giaban += " ";
			
			for(int k = thanhtien.length(); k < 9; k++)
				thanhtien += " ";
			
			System.out.format("                       | %s         %s     %s     %s        %s        %s        %s |\n", sttHd, ngayban, tensach, giaban, soluong, giamgia, thanhtien);
			count++;
		}

		if(count == 0) 
			System.out.println("                       |                                                           Hoa Don Khong Ton Tai.                                              |");
		if(pickNgay == 0 && count != 0)
			System.out.println("                                                                                 Tong Tien: "+TongTien+"");
		
		System.out.println("                       +-------------------------------------------------------------------------------------------------------------------------------+");
		
		System.out.println("                       0. Thoat     1. Quay Lai Menu");
		System.out.print("                     -> ");
		int pick = kb.nextInt();
		switch(pick) {
			case 1: ShowMenu();break;
			default: break;
		}
	}catch(Exception ex){
		System.out.println("SQLException: "+ ex.getMessage());
	}finally {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
		}
		if(cStmt != null) {
			try {
				cStmt.close();
				rs.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
		}
	}
}



//=================================================================================================
//	Add Invoice function

public void LapHD(){ 
    Scanner kb = new Scanner(System.in);
    float sum = 0f;
    String ms;
    int sl;
    String currentHD = "";
   
     try { 
    	 	ConnectDB();
            stmt = conn.createStatement();
            
            if(stmt.execute("select a.stt_hd from hoadon a"));
            	rs = stmt.getResultSet();
            while(rs.next()) {
            	currentHD = rs.getString("stt_hd");
            }
            
            
            char tram = currentHD.charAt(2);
            String tramStr = Character.toString(tram);
            char chuc = currentHD.charAt(3);
            String chucStr = Character.toString(chuc);
            char dv = currentHD.charAt(4);
            String dvStr = Character.toString(dv);
            int currentAmountHD = (Integer.parseInt(tramStr + chucStr + dvStr) + 1);
       
            if(currentAmountHD < 10)
            	currentHD = "HD00" + Integer.toString(currentAmountHD);
            else if(currentAmountHD < 100)
            	currentHD = "HD0" + Integer.toString(currentAmountHD);
            else
            	currentHD = "HD" + Integer.toString(currentAmountHD);
        
            System.out.print("                                                         Nhap So Loai Sach Khach Mua: ");
            int pick = kb.nextInt();
            kb.nextLine();
            for(int i = 0; i < pick; i++){
                
                System.out.print("                                                         Nhap Ma Sach: "); ms = kb.nextLine();
                System.out.print("                                                         Nhap So Luong Sach: "); sl = kb.nextInt();kb.nextLine();
                
                rs = stmt.executeQuery("SELECT * FROM danhmucsach a WHERE (a.ma_sach = "+ "'"+ ms + "')");
                
                while(rs.next()){
                        sum += (sl *  rs.getDouble("don_gia"));
                 } 
                
                if(i == 0) {
                	String sqlInsert = "INSERT INTO hoadon " + " VALUES ('" + currentHD + "','" +  java.time.LocalDate.now()+ "','TN005'," + sum  + ")";
                 
                    PreparedStatement pstm = conn.prepareStatement(sqlInsert);
                    pstm.executeUpdate();
                    pstm.close();
                }
                
                if(i == pick - 1) {
                	String update = "update hoadon a set a.tong_tien = "+ sum +" "+ "where a.stt_hd = " + "'"+currentHD +"'";
                	
                    PreparedStatement pstm3 = conn.prepareStatement(update);
                    pstm3.executeUpdate();
                    pstm3.close();
                }
               
                    rs = stmt.executeQuery("SELECT * FROM danhmucsach a WHERE (a.ma_sach = "+ "'"+ ms + "')");
                   
                        
                        while(rs.next()){
       
                        	cStmt = conn.prepareCall("{call insertInvoices(?, ?, ?, ?, ?, ?)}");
                        	cStmt.setString(1, currentHD);
                        	cStmt.setString(2, ms);
                        	cStmt.setInt(3, sl);
                        	cStmt.setDouble(4, rs.getDouble("don_gia"));
                        	cStmt.setDouble(5, 0.0);
                        	cStmt.setDouble(6, sl * rs.getDouble("don_gia"));
                        	cStmt.executeUpdate();
                        	
                        	String capnhat = "update danhmucsach a set sl_ton = " + rs.getInt("a.sl_ton")+ " - " + sl + " where a.ma_sach = '" + ms +"'";
                            
                            PreparedStatement pstmM = conn.prepareStatement(capnhat);
                            pstmM.executeUpdate();
                        }     
                   
        }
            System.out.println("                                                         Them Hoa Don Thanh Cong !");
     
            
    }catch(Exception ex){
	System.out.println("SQLException: "+ ex.getMessage());
}finally {
	if(rs != null) {
		try {
			rs.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
		}
	}
	if(cStmt != null) {
		try {
			cStmt.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
		}
	}
}
}

//========================================================================================
// Delete Invoices

public void XoaHD() {
	Scanner kb = new Scanner(System.in);
	int sll = 0;
	try {
		this.ConnectDB();
		cStmt = conn.prepareCall("{ call ShowInvoices()}");
		rs = cStmt.executeQuery();

		while(rs.next()) {
			sll++;
		}
		
		System.out.print("                                                         Nhap vao so thu tu hoa don: ");
		String mhd = kb.nextLine();
		
		int stthd = Integer.parseInt(mhd.substring(2));
		
		cStmt = conn.prepareCall("{call DeleteInvoices(?)}");
		cStmt.setString(1, mhd);
		
		if(stthd - 2 > sll) {
			System.out.println("                                                         Hoa Don Khong Ton Tai!");
		}else{
			System.out.println("                                                         Update Successful.");
			cStmt.executeUpdate();
		}
		
	}catch(Exception ex){
		System.out.println("SQLException: "+ ex.getMessage());
	}
	finally {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
			cStmt = null;
		}
		if(cStmt != null) {
			try {
				cStmt.close();
				stmt.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
			}
			finally {
				if(rs != null) {
					try {
						rs.close();
					}catch(SQLException ex) {
						System.out.println("SQLException: "+ ex.getMessage());
					}
				}
				if(cStmt != null) {
					try {
						cStmt.close();
					}catch(SQLException ex) {
						System.out.println("SQLException: "+ ex.getMessage());
					}
				}
			}
		}
	}
}

//===================================================================================
//Statictical Function

public void ThongKe() {
	Scanner kb = new Scanner(System.in);
	try {
		this.ConnectDB();
		System.out.println("                                                         <1> Thong Ke Sach Ban Nhieu Nhat.");
		System.out.println("                                                         <2> Thong Ke Doanh Thu Theo Thang.");
		System.out.print("                                                        > ");
		int select = kb.nextInt();
		switch(select){
			case 1: {
				System.out.println("                                                         So Luong Sach Ban Nhieu Nhat: ");
				
				cStmt = conn.prepareCall("{call StaticticalAmount()}");
				rs = cStmt.executeQuery();
			
				while(rs.next()) {
					String masach = rs.getString("masach");
					String tensach = rs.getString("tensach");
					int sl = rs.getInt("SL");
					System.out.print("                                                         Ma Sach: "+ masach);
					System.out.print("\t Ten Sach: "+ tensach);
					System.out.print("\t So Luong: "+ sl);
					System.out.println();
				}
				break;
			}
			case 2: {
				System.out.println("                                                         Doanh Thu Sach Ban Theo Thang: ");
				
				cStmt = conn.prepareCall("{call StaticticalMonth()}");
				rs = cStmt.executeQuery();
			
				while(rs.next()) {
					String thang = rs.getString("Thang");
					String tt = rs.getString("TongTien");
					
					System.out.print("                                                         Thang: "+ thang);
					System.out.print("\t Tong Tien: "+ tt);
					System.out.println();
			}
				break;
		}
			default:{
				System.out.println("                                                         Nhap sai roi nhap lai di!");
				ThongKe();
			}
		
		}
			System.out.println("                                                         0. Thoat     1. Quay Lai Menu");
			System.out.print("                                                         -> ");
			int pick = kb.nextInt();
			switch(pick) {
				case 1: ShowMenu();break;
				default: break;
			}
		}catch(Exception ex){
			System.out.println("SQLException: "+ ex.getMessage());
	}
		finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: "+ ex.getMessage());
				}
			}
			if(cStmt != null) {
				try {
					cStmt.close();
					stmt.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: "+ ex.getMessage());
				}
			}
		}
}
}




