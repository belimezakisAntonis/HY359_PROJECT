package servlets;

import database.tables.EditDoctorTable;
import database.tables.EditSimpleUserTable;
import mainClasses.Doctor;
import mainClasses.SimpleUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name="Login",value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");


        SimpleUser su = null;
        Doctor doc = null;

        EditSimpleUserTable est = new EditSimpleUserTable();
        EditDoctorTable edt = new EditDoctorTable();
        HttpSession session = request.getSession();


        if (request.getParameter("email") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setEmail(request.getParameter("email"));
                edt.updateEmail(doc.getUsername(), doc.getEmail());


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("password") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setPassword(request.getParameter("password"));
                edt.updatePassword(doc.getUsername(), doc.getPassword());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("firstname") != null) {
            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setFirstname(request.getParameter("firstname"));
                edt.updateFirstName(doc.getUsername(), doc.getFirstname());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("lastname") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setLastname(request.getParameter("lastname"));
                edt.updateLastName(doc.getUsername(), doc.getLastname());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("birthdate") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setBirthdate(request.getParameter("birthdate"));
                edt.updateBirthdate(doc.getUsername(), doc.getBirthdate());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("gender") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setGender(request.getParameter("gender"));
                edt.updateGender(doc.getUsername(), doc.getGender());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("country") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setCountry(request.getParameter("country"));
                edt.updateCountry(doc.getUsername(), doc.getCountry());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("city") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setCity(request.getParameter("city"));
                edt.updateCity(doc.getUsername(), doc.getCity());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("address") != null) {
            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setAddress(request.getParameter("address"));
                edt.updateAddress(doc.getUsername(), doc.getAddress());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("lat") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setLat(Double.parseDouble(request.getParameter("lat")));
                edt.updateLat(doc.getUsername(), doc.getLat());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("lon") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setLon(Double.parseDouble(request.getParameter("lon")));
                edt.updateLon(doc.getUsername(), doc.getLon());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("telephone") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setTelephone(request.getParameter("telephone"));
                edt.updateTelephone(doc.getUsername(), doc.getTelephone());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("height") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setHeight(Integer.parseInt(request.getParameter("height")));
                edt.updateHeight(doc.getUsername(), doc.getHeight());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("weight") != null) {
            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setWeight(Double.parseDouble(request.getParameter("weight")));
                edt.updateWeight(doc.getUsername(), doc.getWeight());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("blooddonor") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setBlooddonor(Integer.parseInt(request.getParameter("blooddonor")));
                edt.updateBloodDonor(doc.getUsername(), doc.getBlooddonor());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("bloodtype") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setBloodtype(request.getParameter("bloodtype"));
                edt.updateBloodType(doc.getUsername(), doc.getBloodtype());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("specialty") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setSpecialty(request.getParameter("specialty"));
                edt.updateSpecialty(doc.getUsername(), doc.getSpecialty());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("doctor_info") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setDoctor_info(request.getParameter("doctor_info"));
                edt.updateDoctorInfo(doc.getUsername(), doc.getDoctor_info());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("certified") != null) {

            try {
                doc = edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString());
                doc.setCertified(Integer.parseInt(request.getParameter("certified")));
                edt.updateCertified(doc.getUsername(), doc.getCertified());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("email") != null || request.getParameter("password") != null
                || request.getParameter("firstname") != null || request.getParameter("lastname") != null
                || request.getParameter("birthdate") != null || request.getParameter("gender") != null
                || request.getParameter("country") != null || request.getParameter("city") != null
                || request.getParameter("address") != null || request.getParameter("lat") != null
                || request.getParameter("lon") != null || request.getParameter("telephone") != null
                || request.getParameter("height") != null || request.getParameter("weight") != null
                || request.getParameter("blooddonor") != null || request.getParameter("bloodtype") != null
                || request.getParameter("specialty") != null || request.getParameter("doctor_info") != null
                || request.getParameter("certified") != null) {

            try {
                doc = edt.databaseToDoctors().get(session.getAttribute("loggedIn").toString());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            response.setStatus(200);
            String doctor = null;
            try (PrintWriter out = response.getWriter()) {
                doctor = "username:" + doc.getUsername() + ",email:" + doc.getEmail() + ",password:" + doc.getPassword() +
                        ",first name:" + doc.getFirstname() + ",last name:" + doc.getLastname() +
                        ",birth date:" + doc.getBirthdate() + ",gender:" + doc.getGender() + ",amka:" + doc.getAmka() +
                        ",country:" + doc.getCountry() + ",city:" + doc.getCity() + ",address:" + doc.getAddress() +
                        ",lat:" + doc.getLat() + ",lon:" + doc.getLon() + ",telephone:" + doc.getTelephone() +
                        ",height:" + doc.getHeight() + ",weight:" + doc.getWeight() + ",blooddonor:" + doc.getBlooddonor() +
                        ",bloodtype:" + doc.getBloodtype() + ",specialty:" + doc.getSpecialty() +
                        ",doctor info:" + doc.getDoctor_info() + ",certified" + doc.getCertified() + ".";
                out.println("<!DOCTYPE html><html lang='en'><head><meta charset=UTF-8></head><body>");
                out.println("<h1><bold>PERSONAL DETAILS</bold></h1><p>" + doctor + "</p></body></html>");


            }
        }


        if (session.getAttribute("loggedIn") != null) {
            try {
                if (edt.databaseUserNameToDoctor(session.getAttribute("loggedIn").toString()) != null) {
                    response.setStatus(202);
                    doc = edt.databaseToDoctors().get(session.getAttribute("loggedIn").toString());
                    response.getWriter().write(doc.getUsername());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        if (session.getAttribute("loggedIn") != null &&
                session.getAttribute("loggedIn").equals("admin")) {
            response.setStatus(201);
            try {
                su = est.databaseToSimpleUsers().get(session.getAttribute("loggedIn").toString());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            response.getWriter().write(su.getUsername());
        }
        if (session.getAttribute("loggedIn") != null &&
                !session.getAttribute("loggedIn").equals("admin")) {

            try {
                if (est.databaseToSimpleUsers().get(session.getAttribute("loggedIn")) != null) {
                    su = est.databaseToSimpleUsers().get(session.getAttribute("loggedIn").toString());
                    response.getWriter().write(su.getUsername());
                    response.setStatus(200);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (session.getAttribute("loggedIn") == null) {
            response.setStatus(403);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        SimpleUser su = new SimpleUser();
        EditSimpleUserTable etu = new EditSimpleUserTable();
        EditDoctorTable edt = new EditDoctorTable();
        HttpSession session = request.getSession(true);
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (edt.databaseUserNameToDoctor(username) != null) {
                session.setAttribute("loggedIn", username);
                response.setStatus(202);
            }


            try {
                if (etu.databaseToSimpleUser(username, password) != null) {

                    if (request.getParameter("username").equals("admin")) {
                        session.setAttribute("loggedIn", username);
                        //    session.setAttribute("admin",username);
                        response.setStatus(201);
                    } else if(!request.getParameter("username").equals("admin")) {
                        session.setAttribute("loggedIn", username);
                        // session.setAttribute("simpleUsers",username);
                        response.setStatus(200);
                    }



                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(edt.databaseToDoctor(username,password) == null && etu.databaseToSimpleUser(username, password) == null){
                response.setStatus(403);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}