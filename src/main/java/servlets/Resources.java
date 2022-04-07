package servlets;

import com.google.gson.Gson;
import database.tables.*;
import mainClasses.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 *
 * @author belimezakis
 */
@WebServlet(name="Resources",value = "/Resources")
public class Resources extends HttpServlet  {
    public static HashMap<String,Doctor> doctors=new HashMap<>();
    public static HashMap<String, SimpleUser> simpleUsers=new HashMap<>();

    public static void setResources(){
        Doctor d=new Doctor();
        d.setDoctor_id(1);
        d.setUsername("papadakis");
        d.setEmail("papadakis@doctor.gr");
        d.setPassword("doctor12*");
        d.setFirstname("Nikos");
        d.setLastname("Papadakis");
        d.setBirthdate("1982-10-03");
        d.setGender("Male");
        d.setAmka("03108200123");
        d.setCountry("Greece");
        d.setCity("Heraklion");
        d.setAddress("Evans 83");
        d.setLat(35.3361866);
        d.setLon(25.1342504);
        d.setTelephone("2810123456");
        d.setHeight(182);
        d.setWeight(80.00);
        d.setBlooddonor(1);
        d.setBloodtype("A+");
        d.setSpecialty("Pathologist");
        d.setDoctor_info("Exei megali empiria se axiologisi emvoliwn.");
        d.setCertified(1);

        Doctor d2=new Doctor();
        d2.setDoctor_id(2);
        d2.setUsername("stefanos");
        d2.setEmail("stefanos@doctor.gr");
        d2.setPassword("abcd12$3");
        d2.setFirstname("Stefanos");
        d2.setLastname("Kapelakis");
        d2.setBirthdate("1958-01-10");
        d2.setGender("Male");
        d2.setAmka("10015800234");
        d2.setCountry("Greece");
        d2.setCity("Heraklion");
        d2.setAddress("Kalokairinou 50");
        d2.setLat(35.3376963);
        d2.setLon(25.1276121);
        d2.setTelephone("2810654321");
        d2.setHeight(170);
        d2.setWeight(68.00);
        d2.setBlooddonor(0);
        d2.setBloodtype("B+");
        d2.setSpecialty("Pathologist");
        d2.setDoctor_info("O kaluteros giatros gia ti gripi.");
        d2.setCertified(1);

        Doctor d3=new Doctor();
        d3.setDoctor_id(3);
        d3.setUsername("papadopoulou");
        d3.setEmail("papadopoulou@doctor.gr");
        d3.setPassword("doct12##");
        d3.setFirstname("Eleni");
        d3.setLastname("Papadopoulou");
        d3.setBirthdate("1980-05-05");
        d3.setGender("Female");
        d3.setAmka("05058000123");
        d3.setCountry("Greece");
        d3.setCity("Heraklion");
        d3.setAddress("Machis Kritis 10");
        d3.setLat(35.3375925);
        d3.setLon(25.1219286);
        d3.setTelephone("2810281028");
        d3.setHeight(170);
        d3.setWeight(60.00);
        d3.setBlooddonor(1);
        d3.setBloodtype("AB+");
        d3.setSpecialty("Pathologist");
        d3.setDoctor_info("Exei kanei metaptyxiakes spoudes stin ameriki.");
        d3.setCertified(1);

        Doctor d4=new Doctor();
        d4.setDoctor_id(4);
        d4.setUsername("aggelopoulos");
        d4.setEmail("aggelopoulos@doctor.gr");
        d4.setPassword("agge58$1");
        d4.setFirstname("Giorgos");
        d4.setLastname("Aggelopoulos");
        d4.setBirthdate("1978-01-12");
        d4.setGender("Male");
        d4.setAmka("01127800111");
        d4.setCountry("Greece");
        d4.setCity("Heraklion");
        d4.setAddress("Leoforos Knossou 200");
        d4.setLat(35.3152534);
        d4.setLon(25.1474208);
        d4.setTelephone("2811111111");
        d4.setHeight(175);
        d4.setWeight(60.00);
        d4.setBlooddonor(1);
        d4.setBloodtype("A-");
        d4.setSpecialty("Pathologist");
        d4.setDoctor_info("Kathigitis iatrikis sto panepistimio.");
        d4.setCertified(1);

        Doctor d5=new Doctor();
        d5.setDoctor_id(5);
        d5.setUsername("papatheodorou");
        d5.setEmail("papatheodorou@doctor.gr");
        d5.setPassword("papap$75");
        d5.setFirstname("Konstantina");
        d5.setLastname("Papatheodorou");
        d5.setBirthdate("1968-01-03");
        d5.setGender("Female");
        d5.setAmka("03016800111");
        d5.setCountry("Greece");
        d5.setCity("Heraklion");
        d5.setAddress("Leoforos 62 Martyron 100");
        d5.setLat(35.3361846);
        d5.setLon(35.3361846);
        d5.setTelephone("2811121111");
        d5.setHeight(160);
        d5.setWeight(65.00);
        d5.setBlooddonor(1);
        d5.setBloodtype("A-");
        d5.setSpecialty("Pathologist");
        d5.setDoctor_info("Exei empiria se zaxaro kai xolisterini.");
        d5.setCertified(1);

        SimpleUser s1=new SimpleUser();
        s1.setUser_id(1);
        s1.setUsername("mountanton");
        s1.setEmail("mike@mike.com");
        s1.setPassword("123456");
        s1.setFirstname("Michalis");
        s1.setLastname("Mountanton");
        s1.setBirthdate("1992-06-03");
        s1.setGender("Male");
        s1.setAmka("03069200000");
        s1.setCountry("Greece");
        s1.setCity("Heraklion");
        s1.setAddress("CSD Voutes");
        s1.setLat(35.3053121);
        s1.setLon(25.0722869);
        s1.setTelephone("1234567890");
        s1.setHeight(173);
        s1.setWeight(153.00);
        s1.setBlooddonor(1);
        s1.setBloodtype("A+");

        SimpleUser s2=new SimpleUser();
        s2.setUser_id(2);
        s2.setUsername("admin");
        s2.setEmail("admin@admin.gr");
        s2.setPassword("admin12*");
        s2.setFirstname("Admin");
        s2.setLastname("Admin");
        s2.setBirthdate("1970-01-01");
        s2.setGender("Male");
        s2.setAmka("01234567890");
        s2.setCountry("Greece");
        s2.setCity("Heraklion");
        s2.setAddress("Liontaria");
        s2.setLat(0.5);
        s2.setLon(0.1);
        s2.setTelephone("281000000");
        s2.setHeight(200);
        s2.setWeight(100.00);
        s2.setBlooddonor(0);
        s2.setBloodtype("A+");

        simpleUsers.put(s1.getUsername(),s1);
        simpleUsers.put(s2.getUsername(),s2);
        doctors.put(d.getUsername(), d);
        doctors.put(d2.getUsername(), d2);
        doctors.put(d3.getUsername(), d3);
        doctors.put(d4.getUsername(), d4);
        doctors.put(d5.getUsername(),d5);
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        EditDoctorTable edt = new EditDoctorTable();
        EditDoctorNoRegistrationTable ednt = new EditDoctorNoRegistrationTable();
        EditSimpleUserTable eut = new EditSimpleUserTable();
        EditTreatmentTable ett = new EditTreatmentTable();
        EditBloodTestTable ebt = new EditBloodTestTable();
        BloodTest bt = new BloodTest();
        HttpSession session = request.getSession(true);
        if(request.getParameter("certify")!=null){
            response.setStatus(200);
            try(PrintWriter out = response.getWriter()){
                out.println("<!DOCTYPE HTML><html><head><meta charset="+'"'+"UTF-8"+'"'+"></head><body>");
                try {
                    ednt.deleteDoctor(request.getParameter("certify"));
                    Doctor doc = ednt.databaseToDoctors().get(request.getParameter("certify"));
                    edt.updateCertified(doc.getUsername(),1);
                    edt.addNewDoctor(doc);
                    out.println("doctor with username"+doc.getUsername()+"added successfully.</body></html>");
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if (request.getParameter("randevouz") != null) {
            Doctor doc = null;
            response.setStatus(200);
            EditRandevouzTable ert = new EditRandevouzTable();

            Randevouz r = new Randevouz();
            try {
                doc = edt.databaseUserNameToDoctor(request.getParameter("randevouz"));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            r.setDoctor_id(doc.getDoctor_id());
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            r.setUser_id(su.getUser_id());
                    r.setPrice(50);
                    r.setDate_time("2022-02-28");
                    r.setStatus("done");
                    r.setDoctor_info("krata covid pass");
                    r.setUser_info("null");
                    r.setSimpleUserName(su.getUsername());
            try {
                ert.createNewRandevouz(r);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try(PrintWriter out = response.getWriter()){
                        out.println("<!DOCTYPE HTML><html><head><meta charset=" + "UTF-8" + "></head><body>");
                        out.println("ΤΟ ΡΑΝΤΕΒΟΥ ΣΑΣ ΚΛΕΙΣΤΗΚΕ ΕΠΙΤΥΧΩΣ");
                        out.println("</body></html>");
                    }


        }
        if(request.getParameter("doctor_id")!=null && request.getParameter("user_id")!=null &&
            request.getParameter("start_date")!=null && request.getParameter("end_date")!=null &&
            request.getParameter("treatment_text")!=null && request.getParameter("blood_test_id")!=null){


            Treatment t = new Treatment();
            t.setDoctor_id(Integer.parseInt(request.getParameter("doctor_id")));
            t.setUser_id(Integer.parseInt(request.getParameter("user_id")));
            t.setStart_date(request.getParameter("start_date"));
            t.setEnd_date(request.getParameter("end_date"));
            t.setTreatment_text(request.getParameter("treatment_text"));
            t.setBloodtest_id(Integer.parseInt(request.getParameter("blood_test_id")));

            try (PrintWriter out = response.getWriter()){
                response.setStatus(200);
                ett.createNewTreatment(t);
                out.println("<!DOCTYPE html><html lang='en'><head><meta charset=UTF-8></head><body>");
                out.println("<h1>Η καταχώρηση της θεραπείας σας πραγματοποιήθηκε επιτυχώς!</h1></body></html>");
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        if(request.getParameter("amka")!=null ) {
            String amka = request.getParameter("amka");
            bt.setAmka(amka);
        }
        if(request.getParameter("test_date")!=null){
            String test_date=request.getParameter("test_date");
            bt.setTest_date(test_date);
        }
        if(request.getParameter("medical_center")!=null){
            String medical_center = request.getParameter("medical_center");
            bt.setMedical_center(medical_center);
        }
        if(request.getParameter("blood_sugar")!=null ) {
            Double blood_sugar = Double.parseDouble(request.getParameter("blood_sugar"));
            bt.setBlood_sugar(blood_sugar);
        }
        if(request.getParameter("blood_sugar_level")!=null){
            String blood_sugar_level=request.getParameter("blood_sugar_level");
        }
        if(request.getParameter("cholesterol")!=null){
            Double cholesterol = Double.parseDouble(request.getParameter("cholesterol"));
            bt.setCholesterol(cholesterol);
        }
        if(request.getParameter("cholesterol_level")!=null ) {
            String cholesterol_level = request.getParameter("cholesterol_level");
        }
        if(request.getParameter("iron")!=null){
            Double iron = Double.parseDouble(request.getParameter("iron"));
            bt.setIron(iron);
        }
        if(request.getParameter("iron_level")!=null){
            String iron_level = request.getParameter("iron_level");
        }
        if(request.getParameter("vitamin_d3")!=null){
            Double vitamin_d3 = Double.parseDouble(request.getParameter("vitamin_d3"));
            bt.setVitamin_d3(vitamin_d3);
        }
        if(request.getParameter("vitamin_d3_level")!=null ) {
            String vitamin_d3_level = request.getParameter("vitamin_d3_level");
        }
        if(request.getParameter("vitamin_b12")!=null){
            Double vitamin_b12 = Double.parseDouble(request.getParameter("vitamin_b12"));
            bt.setVitamin_b12(vitamin_b12);
        }
        if(request.getParameter("vitamin_b12_level")!=null){
            String vitamin_b12_level = request.getParameter("vitamin_b12_level");
        }
        bt.setValues();
        try {
            ebt.createNewBloodTest(bt);
            response.setStatus(200);
            try(PrintWriter out = response.getWriter()){
                out.println("<!DOCTYPE html><html lang='en'><head><meta charset=UTF-8></head><body>");
                out.println("<h1>Η καταχώρηση της εξετάσης σας πραγματοποιήθηκε επιτυχώς!</h1></body></html>");
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        EditDoctorTable edt = new EditDoctorTable();
        EditSimpleUserTable eut = new EditSimpleUserTable();
        HttpSession session = req.getSession(true);
         /*       if (req.getParameter("doctorsSu") != null) {
                    resp.setStatus(200);
                    try (PrintWriter out = resp.getWriter()) {
                        out.println("<!DOCTYPE html><html lang='en'><head><meta charset=UTF-8></head><body>");
                        out.println("<h1><bold>DOCTORS</bold></h1><p>");
                        Iterator hmIterator = edt.databaseToDoctors().entrySet().iterator();
                        while (hmIterator.hasNext()) {

                            Map.Entry mapElement = (Map.Entry) hmIterator.next();
                            out.println("<button name='doctor' onclick='randevouz'>"
                            +edt.databaseToDoctors().get(mapElement.getKey()).getUsername()
                            +"</button");
                        }
                        out.println("</p></body></html>");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }*/
                if (req.getParameter("delete") != null) {
                    String name = (String) req.getParameter("delete");
                    try {
                        edt.databaseToDoctors().remove(name);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        edt.deleteDoctor(name);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    try (PrintWriter out = resp.getWriter()) {
                        resp.setStatus(200);
                        out.println("<!DOCTYPE html><html lang='en'><head><meta charset=UTF-8></head><body>");
                        out.println("<h1><bold>DOCTORS AFTER DELETION</bold></h1><p>" + doctorsList());
                        out.println("</p></body></html>");

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else if(req.getParameter("patient")!=null){
                    String name = req.getParameter("patient");
                    EditRandevouzTable ert = new EditRandevouzTable();
                    EditBloodTestTable ebt = new EditBloodTestTable();

                    Doctor doc = null;
                    try {
                        doc = edt.databaseToDoctors().get(session.getAttribute("loggedIn").toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    resp.setStatus(200);
                    int i=0;
                    try(PrintWriter out = resp.getWriter()){
                        out.println("<!DOCTYPE html><head><meta charset=utf-8></head><body><h1><bold>RESULTS</bold></h1>");
                        out.println("<table border='1'><tr><th>"+req.getParameter("patient")+"</th></tr>");
                        out.println("<tr><th>AMKA"+"</th><th>test date</th><th>medical center</th>");
                        out.println("<th>blood sugar</th><th>blood sugar level</th><th>cholesterol</th>");
                        out.println("<th>cholesterol level</th><th>iron</th><th>iron level</th>");
                        out.println("<th>vitamin d3</th><th>vitamin d3 level</th><th>vitamin b12</th>");
                        out.println("<th>vitamin b12 level</th></tr>");
                        SimpleUser su = eut.databaseUserNameToSimpleUser(req.getParameter("patient"));
                        for(i=0; i<ebt.getBloodTests(su.getAmka()); i++){
                            BloodTest bt = (BloodTest) ebt.getBloodTest(su.getAmka()).get(i);
                            out.println("<tr><td>"+su.getAmka()+"</td><td>"+bt.getTest_date()+"</td>");
                            out.println("<td>"+bt.getMedical_center()+"</td><td>"+bt.getBlood_sugar()+"</td>");
                            out.println("<td>"+bt.getBlood_sugar_level()+"</td><td>"+bt.getCholesterol()+"</td>");
                            out.println("<td>"+bt.getCholesterol_level()+"</td><td>"+bt.getIron()+"</td>");
                            out.println("<td>"+bt.getIron_level()+"</td><td>"+bt.getVitamin_d3()+"</td>");
                            out.println("<td>"+bt.getVitamin_d3_level()+"</td><td>"+bt.getVitamin_b12()+"</td>");
                            out.println("<td>"+bt.getVitamin_b12_level()+"</td></tr>");
                        }
                        out.println("</table><form action='Registration' method='get'>" +
                                "<button name='therapy' value='therapy'>ΚΑΤΑΧΩΡΗΣΗ ΝΕΑΣ ΘΕΡΑΠΕΙΑΣ<" +
                                "</button></form></body></html>");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

        try {
            if (eut.getSimpleUsers() > 0) {
                            if (req.getParameter("deleteUsers") != null) {
                                String name = (String) req.getParameter("deleteUsers");
                                eut.databaseToSimpleUsers().remove(name);
                                eut.deleteSimpleUser(name);
                                try (PrintWriter out = resp.getWriter()) {
                                    resp.setStatus(200);
                                    out.println("<!DOCTYPE html><html lang='en'><head><meta charset=UTF-8></head><body>");
                                    out.println("<h1><bold>REGISTERED USERS AFTER DELETION</bold></h1><p>" + registeredUsers());
                                    out.println("</p></body></html>");

                                }
                            }
                        }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(req.getParameter("email")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setEmail(req.getParameter("email"));
                eut.updateEmail(su.getUsername(),su.getEmail());


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("password")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setPassword(req.getParameter("password"));
                eut.updatePassword(su.getUsername(),su.getPassword());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("firstname")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setFirstname(req.getParameter("firstname"));
                eut.updateFirstName(su.getUsername(),su.getFirstname());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("lastname")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setLastname(req.getParameter("lastname"));
                eut.updateLastName(su.getUsername(),su.getLastname());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("birthdate")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setBirthdate(req.getParameter("birthdate"));
                eut.updateBirthdate(su.getUsername(),su.getBirthdate());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("gender")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setGender(req.getParameter("gender"));
                eut.updateGender(su.getUsername(),su.getGender());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("country")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setCountry(req.getParameter("country"));
                eut.updateCountry(su.getUsername(),su.getCountry());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("city")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setCity(req.getParameter("city"));
                eut.updateCity(su.getUsername(),su.getCity());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("address")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setAddress(req.getParameter("address"));
                eut.updateAddress(su.getUsername(),su.getAddress());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("lat")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setLat(Double.parseDouble(req.getParameter("lat")));
                eut.updateLat(su.getUsername(),su.getLat());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("lon")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setLon(Double.parseDouble(req.getParameter("lon")));
                eut.updateLon(su.getUsername(),su.getLon());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("telephone")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                eut.updateTelephone(su.getUsername(),su.getTelephone());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("height")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setHeight(Integer.parseInt(req.getParameter("height")));
                eut.updateHeight(su.getUsername(),su.getHeight());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("weight")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setWeight(Double.parseDouble(req.getParameter("weight")));
                eut.updateWeight(su.getUsername(),su.getWeight());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("blooddonor")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setBlooddonor(Integer.parseInt(req.getParameter("blooddonor")));
                eut.updateBloodDonor(su.getUsername(),su.getBlooddonor());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("bloodtype")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseUserNameToSimpleUser(session.getAttribute("loggedIn").toString());
                su.setBloodtype(req.getParameter("bloodtype"));
                eut.updateBloodType(su.getUsername(),su.getBloodtype());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(req.getParameter("email")!=null || req.getParameter("password")!=null
        || req.getParameter("firstname")!=null || req.getParameter("lastname")!=null
        || req.getParameter("birthdate")!=null || req.getParameter("gender")!=null
        || req.getParameter("country")!=null || req.getParameter("city")!=null
        || req.getParameter("address")!=null || req.getParameter("lat")!=null
        || req.getParameter("lon")!=null || req.getParameter("telephone")!=null
        || req.getParameter("height")!=null || req.getParameter("weight")!=null
        || req.getParameter("blooddonor")!=null || req.getParameter("bloodtype")!=null){
            SimpleUser su = null;
            try {
                su = eut.databaseToSimpleUsers().get(session.getAttribute("loggedIn").toString());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            resp.setStatus(200);
            try(PrintWriter out = resp.getWriter()) {
                String user = "username:"+su.getUsername()+",email:"+su.getEmail()+",password:"+su.getPassword()+
                        ",first name:"+su.getFirstname()+",last name:"+su.getLastname()+
                        ",birth date:"+su.getBirthdate()+",gender:"+su.getGender()+",amka:"+su.getAmka()+
                        ",country:"+su.getCountry()+",city:"+su.getCity()+",address:"+su.getAddress()+
                        ",lat:"+su.getLat()+",lon:"+su.getLon()+",telephone:"+su.getTelephone()+
                        ",height:"+su.getHeight()+",weight:"+su.getWeight()+",blooddonor:"+su.getBlooddonor()+
                        ",bloodtype:"+su.getBloodtype()+".";
                out.println("<!DOCTYPE html><html lang='en'><head><meta charset=UTF-8></head><body>");
                out.println("<h1><bold>PERSONAL DETAILS</bold></h1><p>"+user+"</p></body></html>");


            }
        }

    }

    public String doctorsList() throws SQLException, ClassNotFoundException {
        EditDoctorTable edt =new EditDoctorTable();
        if (edt.records() > 0) {
            Gson gson = new Gson();
            String json = null;

            for (HashMap.Entry mapElement : edt.databaseToDoctors().entrySet()) {


                Doctor value = ((Doctor) mapElement.getValue());
                if (json == null) {
                    json = "[{" + '"' + "username" + '"' + ":" + value.getUsername() +
                            "," + '"' + "first name" + '"' + ":" + value.getFirstname() +
                            "," + '"' + "last name" + '"' + ":" + value.getLastname() +
                            "," + '"' + "birth date" + '"' + ":" + value.getBirthdate() + "}]";
                } else {
                    json += "[{" + '"' + "username" + '"' + ":" + value.getUsername() +
                            "," + '"' + "first name" + '"' + ":" + value.getFirstname() +
                            "," + '"' + "last name" + '"' + ":" + value.getLastname() +
                            "," + '"' + "birth date" + '"' + ":" + value.getBirthdate() + "}]";
                }
            }
            return json;
        }
        return null;
    }

    public String registeredUsers() throws SQLException, ClassNotFoundException {
        EditSimpleUserTable eut =new EditSimpleUserTable();
        if (eut.getSimpleUsers() > 0) {
            Gson gson = new Gson();
            String json = null;

            for (HashMap.Entry mapElement : eut.databaseToSimpleUsers().entrySet()) {


                SimpleUser value = ((SimpleUser) mapElement.getValue());
                if (json == null) {
                    json = "[{" + '"' + "username" + '"' + ":" + value.getUsername() +
                            "," + '"' + "first name" + '"' + ":" + value.getFirstname() +
                            "," + '"' + "last name" + '"' + ":" + value.getLastname() +
                            "," + '"' + "birth date" + '"' + ":" + value.getBirthdate() + "}]";
                } else {
                    json += "[{" + '"' + "username" + '"' + ":" + value.getUsername() +
                            "," + '"' + "first name" + '"' + ":" + value.getFirstname() +
                            "," + '"' + "last name" + '"' + ":" + value.getLastname() +
                            "," + '"' + "birth date" + '"' + ":" + value.getBirthdate() + "}]";
                }
            }
            return json;
        }
        return null;
    }
}
