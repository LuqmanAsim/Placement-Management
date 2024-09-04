import java.util.*;
import java.io.*;
import java.sql.*;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class StudLogin
{
    public int f1()
    {
        int xpin,pin=0;
        int i=0,xid,id=0;
        System.out.println("\nENTER USER ID");
        Scanner s = new Scanner(System.in);
        xid=s.nextInt();

        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","123456");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from login");

            while(rs.next())
            {
                id=rs.getInt(1);
                pin=rs.getInt(2);

                if(id==xid)
                {
                    i=1;
                    break;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        if(i==1)
        {
            System.out.flush();
            System.out.println("ENTER YOUR PIN");
            do
            {
                xpin=Integer.parseInt(s.next());
                if(xpin==pin)
                {
                    System.out.println("\nLOGIN SUCCESSFULL");
                    break;
                }
                else
                {
                    System.out.println("\nINCORRECT PIN PLEASE ENTER AGAIN");
                }
            }while(true);
        }
        else
        {
            try
            {
                System.out.println("CREATE A PIN FOR YOUR ACCOUNT");
                xpin = s.nextInt();

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","123456");

                PreparedStatement pst = con.prepareStatement("insert into login values(?,?)");

                pst.setInt(1,xid);
                pst.setInt(2,xpin);

                int count=pst.executeUpdate();
                if(count>0)
                {
                    System.out.println("\nACCOUNT CREATED SUCCESSFULLY\n\n");
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        return xid;
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class AdminLogin
{
    public void f2()
    {
        String pin,id;
        Scanner s = new Scanner(System.in);
        do
        {
            System.out.println("ENTER USER ID");
            id=s.nextLine();
            System.out.println("ENTER PIN");
            pin=s.nextLine();
            if(id.equals("admin") && pin.equals("1234"))
            {
                System.out.println("\nLOGIN SUCCESSFUL");
                break;
            }
            else
                System.out.println("\nUSER ID OR PIN INCORRECT PLEASE TRY AGAIN");
        }while(true);
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class stud
{
    public void f3(int xid)
    {
        int i=0,id;
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","123456");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from stud");

            while(rs.next())
            {
                id=rs.getInt(2);
                if(id==xid)
                {
                    i=1;
                    break;
                }
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        if(i==1)
        {
            f4(xid);
        }
        else
        {
            f5(xid);
        }
    }
    public void f4(int xid)
    {
        String name="",dept="",type="",gv;
        int i,j=0,id=0,blogs=0;
        float cgpa=0;
        Scanner s = new Scanner(System.in);
        do
        {
            System.out.println("\nCHOOSE");
            System.out.println("1. VIEW DETAILS\n2. UPDATE DETAILS\n3. DELETE DATA\n4. EXIT");
            i=s.nextInt();
            if(i==1)
            {
                j=0;
                try 
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","123456");

                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select * from stud");

                    while(rs.next())
                    {
                        name=rs.getString(1);
                        id=rs.getInt(2);
                        dept=rs.getString(3);
                        cgpa=rs.getFloat(4);
                        blogs=rs.getInt(5);
                        type=rs.getString(6);
                        if(id==xid)
                        {
                            j=1;
                            break;
                        }
                    }
                    con.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                if(j==1)
                    System.out.println("\n\nNAME: "+name+"\nID: "+id+"\nDEPT: "+dept+"\nCGPA: "+cgpa+"\nBACKLOGS: "+blogs+"\nSKILL: "+type);
                else
                    System.out.println("\n\nNO DATA");
                System.out.println("\n\nPRESS ANY KEY TO CONTINUE . . .");
                gv=s.next();
            }
            else if(i==2)
            {
                update ob4 = new update();
                if(j==1)
                    ob4.f4(xid);
                else
                    System.out.println("\n\nNO DATA");
            }
            else if(i==3)
            {
                delete ob5 = new delete();
                if(j==1)
                    ob5.f5(xid);
                else
                    System.out.println("\n\nNO DATA");
            }
            else if(i==4)
                break;
            else
                System.out.println("INVALID INPUT");
            
        }while(true);
    }
    public void f5(int xid) 
    {
        Scanner s=new Scanner(System.in);
        int id,blogs,j=0;
        float cgpa;
        String name,type="",dept;

        System.out.println("ENTER YOUR DETAILS");
        System.out.print("NAME: ");
        name=s.next();
        id=xid;
        System.out.print("DEPT: ");
        dept=s.next();
        System.out.print("CGPA: ");
        cgpa=s.nextFloat();
        System.out.print("NO. OF BACKLOGS: ");
        blogs=s.nextInt();
        
        System.out.println("\nCHOOSE YOUR SKILL");
        do
        {
            System.out.println("1. FULLSTACK\n2. FRONTEND\n3. BACKEND\n4. NONE");
            j=s.nextInt();
            if(j==1 || j==2 || j==3 || j==4)
                break;
            else
                System.out.println("\nINVALID INPUT CHOOSE AGAIN");

        }while(true);

        if (j==1)
            type="fullstack";
        else if(j==2)
            type="frontend";
        else if(j==3)
            type="backend";
        else
            type="none";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","123456");

            PreparedStatement pst = con.prepareStatement("insert into stud values(?,?,?,?,?,?)");

            pst.setString(1,name);
            pst.setInt(2,id);
            pst.setString(3,dept);
            pst.setFloat(4,cgpa);
            pst.setInt(5,blogs);
            pst.setString(6,type);

            int count=pst.executeUpdate();
            if(count>0)
                System.out.println("\nINSERT SUCCESSFUL");

            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        f4(xid);
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class update
{
    public void f4(int xid)
    {
        Scanner s = new Scanner(System.in);
        String name,dept,type;
        float cgpa;
        int op,id=xid,blogs,count=0;

        System.out.println("\nCHOOSE WHAT TO UPDATE");
        do
        {
            System.out.println("1. NAME\n2. DEPT\n3. CGPA\n4. BACKLOGS\n5. SKILL");
            op=s.nextInt();
            if(op>=1 && op<=5)
                break;
            else
                System.out.println("\nINVALID SELECTION CHOOSE AGAIN");
        } while (true);


        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","123456");
            
            Statement st=con.createStatement();
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

            switch (op) {
                case 1:
                    System.out.print("ENTER\nNAME: ");
                    name=br.readLine();
                    count=st.executeUpdate("update STUD set name=\'"+name+"\' where id="+id);
                    break;
                case 2:
                    System.out.print("DEPT: ");
                    dept=br.readLine();
                    count=st.executeUpdate("update STUD set dept=\'"+dept+"\' where id="+id);
                    break;
                case 3:
                    System.out.print("CGPA: ");
                    cgpa=Float.parseFloat(br.readLine());
                    count=st.executeUpdate("update STUD set cgpa="+cgpa+" where id="+id);
                    break;
                case 4:
                    System.out.print("BACKLOGS: ");
                    blogs=Integer.parseInt(br.readLine());
                    count=st.executeUpdate("update STUD set backlogs="+blogs+" where id="+id);
                    break;
                case 5:
                    System.out.print("TYPE: ");
                    type=br.readLine();
                    count=st.executeUpdate("update STUD set type=\'"+type+"\' where id="+id);
                    break;
            }

            if(count>0)
                System.out.println("\nUPDATE SUCCESSFUL");

            con.close();
        } 
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class delete
{
    public void f5(int xid)
    {
        int id=xid;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","123456");

            Statement st = con.createStatement();

            int count=st.executeUpdate("delete from stud where id="+id);
            if(count>0)
            {
                System.out.println("\nDELETION SUCCESSFUL");
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class admin
{
    public void f6()
    {
        Scanner s = new Scanner(System.in);
        int k;
        comp ob7=new comp();
        sdetails ob9=new sdetails();

        System.out.println("\nCHOOSE");
        do
        {
            System.out.println("1. ADD NEW COMPANY\n2. VIEW COMPANY DETAILS\n3. VIEW STUDENT DETAILS\n4. SORT STUDENTS\n5. PLACED STUDENTS\n6. EXIT");
            k=s.nextInt();
            if(k==1)
                ob7.f7();
            else if(k==2)
                ob7.f8();
            else if(k==3)
                ob9.f9();
            else if(k==4)
                ob9.f10();
            else if(k==5)
                ob9.f11();
            else if(k==6)
                break;
            else
                System.out.println("\nINVALID INPUT - CHOOSE AGAIN");
        }while(true);
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class comp
{
    public void f7()
    {
        Scanner s=new Scanner(System.in);
        int j,id,blogs;
        float cgpa;
        String name,type;

        System.out.println("\nENTER COMPANY DETAILS");
        System.out.print("COMPANY NAME: ");
        name=s.next();
        System.out.print("COMPANY ID: ");
        id=s.nextInt();
        System.out.print("REQUIRED CGPA: ");
        cgpa=s.nextFloat();
        System.out.print("MAX NO.OF BACKLOGS: ");
        blogs=s.nextInt();
        
        System.out.println("\nCHOOSE SKILL OF REQ DEVELOPER");
        do
        {
            System.out.println("1. FULLSTACK\n2. FRONTEND\n3. BACKEND\n4. NONE");
            j=s.nextInt();
            if(j==1 || j==2 || j==3 || j==4)
                break;
            else
                System.out.println("\nINVALID INPUT CHOOSE AGAIN");

        }while(true);

        if (j==1)
            type="fullstack";
        else if(j==2)
            type="frontend";
        else if(j==3)
            type="backend";
        else
            type="none";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","123456");

            PreparedStatement pst = con.prepareStatement("insert into comp values(?,?,?,?,?)");

            pst.setString(1,name);
            pst.setInt(2,id);
            pst.setFloat(3,cgpa);
            pst.setInt(4,blogs);
            pst.setString(5,type);

            int count=pst.executeUpdate();
            if(count>0)
                System.out.println("\nCONPANY ADDED SUCCESSFULLY");

            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void f8()
    {
        try 
        {
            String name,type;
            int id,blogs;
            float cgpa;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","123456");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from comp");

            while(rs.next())
            {
                name=rs.getString(1);
                id=rs.getInt(2);
                cgpa=rs.getFloat(3);
                blogs=rs.getInt(4);
                type=rs.getString(5);

                System.out.println("NAME        : "+name+"\nID          : "+id+"\nREQ_CGPA    : "+cgpa+"\nMAX_BACKLOGS: "+blogs+"\nSKILL    : "+type+"\n\n");
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class sdetails
{
    public void f9()
    {
        try 
        {
            String name,type,dept;
            int id,blogs;
            float cgpa;

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","123456");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from stud");

            while(rs.next())
            {
                name=rs.getString(1);
                id=rs.getInt(2);
                dept=rs.getString(3);
                cgpa=rs.getFloat(4);
                blogs=rs.getInt(5);
                type=rs.getString(6);
                System.out.println("NAME        : "+name+"\nID          : "+id+"\nDEPARTMENT  : "+dept+"\nCGPA        : "+cgpa+"\nBACKLOGS    : "+blogs+"\nSKILL    : "+type+"\n\n");
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void f10()
    {
        try 
        {
            String name,type,dept;
            int id,blogs;
            float cgpa;

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","123456");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from stud order by cgpa desc");

            while(rs.next())
            {
                name=rs.getString(1);
                id=rs.getInt(2);
                dept=rs.getString(3);
                cgpa=rs.getFloat(4);
                blogs=rs.getInt(5);
                type=rs.getString(6);
                System.out.println("NAME        : "+name+"\nID          : "+id+"\nDEPARTMENT  : "+dept+"\nCGPA        : "+cgpa+"\nBACKLOGS    : "+blogs+"\nSKILL    : "+type+"\n\n");
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void f11()
    {
        try 
        {
            String sname,cname;
            float ccgpa,scgpa;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","123456");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select s.name,c.name,s.cgpa,c.cgpa from stud s,comp c where s.cgpa>=c.cgpa and c.backlogs>=s.backlogs");

            while(rs.next())
            {
                sname=rs.getString(1);
                cname=rs.getString(2);
                scgpa=rs.getFloat(3);
                ccgpa=rs.getFloat(4);
                System.out.println("STUD_NAME : "+sname+"\nCOMP_NAME : "+cname+"\nSTUD_CGPA : "+scgpa+"\nREQ_CGPA  : "+ccgpa+"\n\n");
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Main
{
    public static void main(String[] args)
    {
        int id=0;
        System.out.println("\n------------------------------------------------PLACEMENTS------------------------------------------------\n");
        int op;
        Scanner s = new Scanner(System.in);

        System.out.println("CHOOSE");
        do
        {
            System.out.println("1. STUDENT LOGIN\n2. ADMIN LOGIN");
            op=s.nextInt();
            if(op==1)
            {
                StudLogin ob1 = new StudLogin();
                id=ob1.f1();
                break;
            }
            else if(op==2)
            {
                AdminLogin ob2 = new AdminLogin();
                ob2.f2();
                break;
            }
            else
            {
                System.out.println("\nINCORRECT INPUT - PLEASE ENTER CHOOSE AGAIN");
            }
        }while(op!=1 || op!=2);

        if(op==1)
        {
            stud ob3 = new stud();
            ob3.f3(id);
        }
        else
        {
            admin ob6 = new admin();
            ob6.f6();
        }
    }
}
