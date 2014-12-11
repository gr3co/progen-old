import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

class Profile extends JFrame implements ActionListener{

    JLabel last,first,m,email,pic, desc, gend, bd, st;
    JTextField lastf, firstf, mf, emailf,picf,dd,yy;
    JComboBox gender, mm, state;
    JTextArea descf;
    JButton choose,submit;
    JFileChooser chooser;

    public Profile(){
        super("Profile Generator v1.05 by sgreco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        last = new JLabel("Last:");
        first = new JLabel("First:");
        m = new JLabel("M:");
        email = new JLabel("E-mail:");
        lastf = new JTextField(16);
        firstf = new JTextField(12);
        mf = new JTextField(1);
        desc = new JLabel("Description:");
        descf = new JTextArea(4,32);
        descf.setBorder(BorderFactory.createEtchedBorder());
        emailf = new JTextField(32);
        pic = new JLabel("Photo:");
        picf = new JTextField(32);
        choose = new JButton("Open");
        choose.addActionListener(this);
        submit = new JButton("Submit");
	String[] genders = {"","Male", "Female", "Both", "Neither"};
	String[] months = {"","Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
			   "Aug", "Sept", "Oct", "Nov", "Dec"};
	String[] states = {"","AK","AL","AR","AS","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID",
			  "IL","IN","KS","KY","LA","MA","MD","ME","MH","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY",
			  "OH","OK","OR","PA","PR","PW","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY","Other"};
	gender = new JComboBox(genders);
	mm = new JComboBox(months);
	state = new JComboBox(states);
	bd = new JLabel("Birthday:");
	gend = new JLabel("Gender:");
	st = new JLabel("State:");
	dd = new JTextField(2);
	yy = new JTextField(4);
	mm.addActionListener(this);
	gender.addActionListener(this);
        submit.addActionListener(this);
        Box stuff = Box.createVerticalBox();
        Box img = Box.createHorizontalBox();
        Box lst = Box.createHorizontalBox();
        Box eml = Box.createHorizontalBox();
        Box dsc = Box.createHorizontalBox();
	Box gbd = Box.createHorizontalBox();
        dsc.add(desc);
        dsc.add(descf);
        lst.add(last);
        lst.add(lastf);
        lst.add(first);
        lst.add(firstf);
        lst.add(m);
        lst.add(mf);
        eml.add(email);
        eml.add(emailf);
        img.add(pic);
        img.add(picf);
        img.add(choose);
	gbd.add(bd);
	gbd.add(mm);
	gbd.add(dd);
	gbd.add(yy);
	gbd.add(gend);
	gbd.add(gender);
	gbd.add(st);
	gbd.add(state);
        stuff.add(lst);
        stuff.add(eml);
	stuff.add(gbd);
        stuff.add(img);
        stuff.add(dsc);
        stuff.add(submit);
        add(stuff);
        pack();
        setVisible(true);
    }

    void writeFile(){
	String fs, ms, ls, es, ps, ds, mms, ss, gs;
	int dds, yys;
	fs = ms = ls = es = ps = ds = mms = ss = gs = "";
	dds = yys = 0;
	try{
	    fs = firstf.getText();
	    ms = mf.getText().substring(0,1);
	    ls = lastf.getText();
	    es = emailf.getText();
	    ps = picf.getText();
	    ds = descf.getText();
	    dds = Integer.parseInt(dd.getText().substring(0,2));
	    yys = Integer.parseInt(yy.getText().substring(0,4));
	    mms = (String)mm.getSelectedItem();
	    ss = (String)state.getSelectedItem();
	    gs = (String)gender.getSelectedItem();
	}	    
	catch(Exception e){
	    System.out.println("Unable to read from input");
	    JOptionPane.showMessageDialog(this, "Something went wrong! Please try again.", 
					  "Error!", JOptionPane.ERROR_MESSAGE);
	    System.exit(0);
	}
	try{
	    String filename = ls + fs + ".html";
	    fs += " ";
	    BufferedWriter out = new BufferedWriter(new FileWriter(filename));
	    BufferedReader sty = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("Components/style.txt")));
	    out.write("<html>\n<head>\n<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />\n<title>I haven't thought of a good title yet | " + 
		      fs + " " + ls + "'s Profile</title>\n</head>\n");
	    for (String s = sty.readLine(); s != null; s = sty.readLine()){
		out.write(s);
	    }
	    String hstuff = " <div id=\"header\"><h1>" + fs + ls + "'s Profile</h1></div>" + 
		"\n<div id=\"main\"><img src=\"" + ps + "\" alt=\"Profile Picture\" align=\"left\" width=\"200\">" + 
		"\n<p id =\"name\"><b>NAME:</b> " + fs + ms + " " + ls + 
		"</p>\n<p id =\"email\"><b>EMAIL:</b> " + es + 
		"</p>\n<p id = \"birthday\"><b>BIRTHDAY:</b> " + mms + " " + dds + ", " + yys + 
		"</p>\n<p id = \"gender\"><b>GENDER:</b> " + gs +
		"</p>\n<p id = \"state\"><b>LOCATION:</b> " + ss +
		"</p>\n<p id = \"description\"><b>ABOUT ME:</b> " + ds + "</p>\n";
	    out.write(hstuff);
	    out.close();
            System.out.println(filename + " was created successfully.");
	    JOptionPane.showMessageDialog(this, filename + " was created successfully.", "Success!",JOptionPane.PLAIN_MESSAGE);
	} 
	catch (IOException e){
	    System.out.println("Unable to write file");
	    JOptionPane.showMessageDialog(this, "Unable to write file", "Error", JOptionPane.ERROR_MESSAGE);
	    System.exit(0);
	}
    }
    
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == submit){
            writeFile();
            System.exit(0);
        }
        if (e.getSource() == choose){
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(".."));
            chooser.setDialogTitle("Choose a profile pic");
            chooser.setFileFilter(new ImageFileFilter());
            int retVal = chooser.showOpenDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
				File p = chooser.getSelectedFile();
                picf.setText(p.getAbsolutePath());
            }
        }
    }

    public static void main(String[] args) throws Exception{
	Profile p = new Profile();
    }
    
    private class ImageFileFilter extends javax.swing.filechooser.FileFilter{
        private final String[] okFileExtensions = 
        new String[] {"jpg", "png", "gif", "bmp"};
        
        public boolean accept(File file)
        {
            for (String extension : okFileExtensions)
            {
                if (file.getName().toLowerCase().endsWith(extension))
                {
                    return true;
                }
            }
            return false;
        }
        
        public String getDescription(){
            return "Images only";
        }
    }
}