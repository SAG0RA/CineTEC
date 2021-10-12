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
    public class EmpleadoController : Controller
    {
        private readonly IConfiguration _configuration;

        public EmpleadoController(IConfiguration configuration)
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
                   nombre as ""nombre"",
                   rol as ""rol"",
                   usuario as ""usuario"",
                   contraseña as ""contraseña""
                from Empleado
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
                   nombre as ""nombre"",
                   rol as ""rol"",
                   usuario as ""usuario"",
                   contraseña as ""contraseña""
                from Empleado
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
        public JsonResult Post(Empleado el)
        {
            string query = @"
                insert into Empleado(cedula, nombre, rol, usuario, contraseña) 
                values             (@cedula, @nombre, @rol, @usuario, @contraseña)            
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon");
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@cedula", el.cedula);
                    myCommand.Parameters.AddWithValue("@nombre", el.nombre);
                    myCommand.Parameters.AddWithValue("@rol", el.rol);
                    myCommand.Parameters.AddWithValue("@usuario", el.usuario);
                    myCommand.Parameters.AddWithValue("@contraseña", el.contraseña);

                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult("Empleado añadido exitosamente");
        }


        [HttpPut]
        public JsonResult Put(Empleado el)

        {
            string query = @"
                update Empleado
                set nombre = @nombre,
                    rol = @rol,
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
                    myCommand.Parameters.AddWithValue("@cedula", el.cedula);
                    myCommand.Parameters.AddWithValue("@nombre", el.nombre);
                    myCommand.Parameters.AddWithValue("@rol", el.rol);
                    myCommand.Parameters.AddWithValue("@usuario", el.usuario);
                    myCommand.Parameters.AddWithValue("@contraseña", el.contraseña);

                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult("Empleado actualizado exitosamente");
        }

        [HttpDelete("{id}")]
        public JsonResult Delete(int id)

        {
            string query = @"
                delete from Empleado
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
            return new JsonResult("Empleado borrado exitosamente");
        }

    }
}