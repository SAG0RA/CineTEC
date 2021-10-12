using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Npgsql;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using CineTECAPI.Models;

namespace CineTECAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ClienteController : Controller
    {
        private readonly IConfiguration _configuration;

        public ClienteController(IConfiguration configuration)
        {
            _configuration = configuration; //Inyeccion de dependencia
        }

        [HttpGet]
        public JsonResult Get() //Este procedimiento es necesario para que el rest api pueda conectarse a la base de datos postgresql
                                //y pueda desplegar los datos como json
        {
            //String de sql para recibir los datos de la tabla
            string query = @"                                   
                select cedula as ""cedula"",
                   pnombre as ""pnombre"",
                   snombre as ""snombre"",
                   apellido as ""apellido"",
                   telefono as ""telefono"",
                   to_char(fechanac, 'YYYY-MM-DD') as ""fechanac"",
                   edad as ""edad"",
                   usuario as ""usuario"",
                   contraseña as ""contraseña""
                from Cliente
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon"); //Obtiene el string de postgres 
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource)) //Setea la configuracion de la conexion
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon)) //Lee los datos de la tabla 
                {
                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult(table); //Devuelve los datos de la tabla en formato JSON

        }

        [HttpGet("{id}")]
        public JsonResult GetbyId(int id)
        {
            string query = @"                                   
                select cedula as ""cedula"",
                   pnombre as ""pnombre"",
                   snombre as ""snombre"",
                   apellido as ""apellido"",
                   telefono as ""telefono"",
                   to_char(fechanac, 'YYYY-MM-DD') as ""fechanac"",
                   edad as ""edad"",
                   usuario as ""usuario"",
                   contraseña as ""contraseña""
                where cedula = @cedula
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon"); //Obtiene el string de postgres 
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource)) //Setea la configuracion de la conexion
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon)) //Lee los datos de la tabla 
                {
                    myCommand.Parameters.AddWithValue("@cedula", id);
                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult(table); //Devuelve los datos de la tabla en formato JSON
        }

        [HttpPost]
        public JsonResult Post(Cliente cl)
        {
            string query = @"
                insert into Cliente(cedula, pnombre, snombre, apellido, telefono, fechanac, edad, usuario, contraseña) 
                values             (@cedula, @pnombre, @snombre, @apellido, @telefono, @fechanac, @edad, @usuario, @contraseña)              
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon");
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@cedula", cl.cedula);
                    myCommand.Parameters.AddWithValue("@pnombre", cl.pnombre);
                    myCommand.Parameters.AddWithValue("@snombre", cl.snombre);
                    myCommand.Parameters.AddWithValue("@apellido", cl.apellido);
                    myCommand.Parameters.AddWithValue("@telefono", cl.telefono);
                    myCommand.Parameters.AddWithValue("@fechanac", Convert.ToDateTime(cl.fechanac));
                    myCommand.Parameters.AddWithValue("@edad", cl.edad);
                    myCommand.Parameters.AddWithValue("@usuario", cl.usuario);
                    myCommand.Parameters.AddWithValue("@contraseña", cl.contraseña);

                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult("Cliente añadido exitosamente");
        }


        [HttpPut]
        public JsonResult Put(Cliente cl)

        {
            string query = @"
                update Cliente 
                set pnombre = @pnombre,
                    snombre = @snombre,
                    apellido = @apellido,
                    telefono = @telefono,
                    fechanac = @fechanac,
                    edad = @edad,
                    usuario = @usuario,
                    contraseña = @contraseña
                where cedula=@cedula
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon");
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@cedula", cl.cedula);
                    myCommand.Parameters.AddWithValue("@pnombre", cl.pnombre);
                    myCommand.Parameters.AddWithValue("@snombre", cl.snombre);
                    myCommand.Parameters.AddWithValue("@apellido", cl.apellido);
                    myCommand.Parameters.AddWithValue("@telefono", cl.telefono);
                    myCommand.Parameters.AddWithValue("@fechanac", Convert.ToDateTime(cl.fechanac));
                    myCommand.Parameters.AddWithValue("@edad", cl.edad);
                    myCommand.Parameters.AddWithValue("@usuario", cl.usuario);
                    myCommand.Parameters.AddWithValue("@contraseña", cl.contraseña);

                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult("Cliente actualizado exitosamente");
        }

        [HttpDelete("{id}")]
        public JsonResult Delete(int id)

        {
            string query = @"
                delete from Cliente
                where cedula=@cedula
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon");
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon))
                {

                    myCommand.Parameters.AddWithValue("@cedula", id);
                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult("Cliente borrado exitosamente");
        }

    }
}