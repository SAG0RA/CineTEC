using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace CineTECAPI.Models
{
    public class Sala
    {
        public int id { get; set; }
        public string sucursal { get; set; }
        public int filas { get; set; }
        public int columnas { get; set; }

        //public int tablaexcel { get; init; }

    }
}
