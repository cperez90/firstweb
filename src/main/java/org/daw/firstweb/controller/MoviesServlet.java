package org.daw.firstweb.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.daw.firstweb.model.Movie;
import org.daw.firstweb.service.MovieServiceOrmImpl;
import org.daw.firstweb.service.MovieServiceStaticImpl;
import org.daw.firstweb.util.JdbcConnector;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "movieServlet", value = "/movies")
public class MoviesServlet extends HttpServlet {

    MovieServiceOrmImpl service = new MovieServiceOrmImpl();
    //JdbcConnector jdbcConnector = new JdbcConnector();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();

        out.println("<h1>Hola som una Movie</h1>");*/

        /*String nom = req.getParameter("nom");

        req.setAttribute("nomPeli", "The Matrix");

        req.getRequestDispatcher("movie.js").forward(req,resp);*/

        /*service.addMovie(new Movie(1, "Inception", "A thief enters dreams to steal secrets."));
        service.addMovie(new Movie(2, "The Matrix", "A hacker discovers the truth about reality."));
        service.addMovie(new Movie(3, "Interstellar", "Explorers travel through a wormhole to save humanity."));
        service.addMovie(new Movie(4, "The Dark Knight", "Batman faces the Joker in Gotham City."));
        service.addMovie(new Movie(5, "Avatar", "A soldier becomes part of an alien world."));*/

        //conncexio jdbc
        //load driver

        /*String nameParam = "rings";
        Connection conn = null;
        try {
            conn = JdbcConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {

            PreparedStatement pst = conn.prepareStatement("select * from movies" where name like ? and rate > ?);
            //pst.setString(1,nameParam);
            //pst.setFloat(2,4);
            ResultSet result = pst.executeQuery();
            while (result.next()){
                int movieId = result.getInt("id");
                String movieTitle = result.getString("title");
                String movieDescription = result.getString("description");
                int movieYear = result.getInt("year");
                Movie movie = new Movie(movieId,movieTitle,movieDescription,movieYear,0);

                service.addMovie(movie);
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e + " No hace la conexion");
        }*/

        if (req.getParameter("id") == null) {
            req.setAttribute("movies", service.findAll());
            req.getRequestDispatcher("movies.jsp").forward(req, resp);
        } else {
            Long id = Long.valueOf(req.getParameter("id"));
            if (id >= 0 && id < service.findAll().size()) {
                req.setAttribute("movie", service.findById(id));
                req.getRequestDispatcher("movie.jsp").forward(req, resp);
            }
        }


    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        try {
            String method = req.getParameter("_method");

            if ("DELETE".equalsIgnoreCase(method)) {
                doDelete(req, resp);
                return;
            }

            String movieName = req.getParameter("title");
            String movieDescription = req.getParameter("description");
            String movieYear = req.getParameter("year");

            if (movieName != null && movieDescription != null && movieYear != null) {
                Movie newMovie = new Movie(movieName, movieDescription, Integer.parseInt(movieYear));
                service.addMovie(newMovie);
            }
            req.setAttribute("movies", service.findAll());
            req.getRequestDispatcher("movies.jsp").forward(req, resp);
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error: Falta el parámetro 'id'.");
            return;
        }

        try {
            Long id = Long.parseLong(idParam);
            service.deleteMovieById(id);

            req.setAttribute("movies", service.findAll());
            req.getRequestDispatcher("movies.jsp").forward(req, resp);

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
