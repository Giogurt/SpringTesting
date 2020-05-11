function octalian(d) {
    var result = 0;
    var division = d;
    var remainder = 0;
    var octal = "";
    
    while (division > 0) {
        remainder = division % 8;
        division = Math.floor(division/8);
        octal = remainder.toString() + octal;
    }
    
    result = parseInt(octal);
    return result;
}

exports.octalian = octalian;