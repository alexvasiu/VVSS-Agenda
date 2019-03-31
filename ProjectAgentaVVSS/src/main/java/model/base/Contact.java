package model.base;

import exceptions.InvalidFormatException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Contact {
	private String Name;
	private String Email;
	private String Telefon;
	private String Address;

	public Contact(){
		Name = "";
		Email = "";
		Telefon = "";
		Address = "";
	}
	
	public Contact(String name, String address, String telefon, String email) throws InvalidFormatException{
		if (name.length() == 0)
			throw new InvalidFormatException("Cannot convert", "Error message - Empty name");

		if (email.length() == 0)
			throw new InvalidFormatException("Cannot convert", "Error message - Empty email");

		if (name.length() < 5 || name.length() > 150)
			throw new InvalidFormatException("Cannot convert", "Error message - Name length error");

		if (email.length() < 10 || email.length() > 100)
			throw new InvalidFormatException("Cannot convert", "Error message - Email length error");

		if (!validTelefon(telefon)) throw new InvalidFormatException("Cannot convert", "Invalid phone number");
		if (!validName(name)) throw new InvalidFormatException("Cannot convert", "Invalid name");
		if (!validAddress(address)) throw new InvalidFormatException("Cannot convert", "Invalid address");
		if (!validEmail(email)) throw new InvalidFormatException("Cannot convert", "Error message - Incorect email");
		Name = name;
        Address = address;
		Telefon = telefon;
		Email = email;
	}

    private boolean validEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public String getName() {
		return Name;
	}

	public void setName(String name) throws InvalidFormatException {
		if (!validName(name)) throw new InvalidFormatException("Cannot convert", "Invalid name");
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) throws InvalidFormatException {
		if (!validAddress(email)) throw new InvalidFormatException("Cannot convert", "Invalid email");
		Email = email;
	}

	public String getTelefon() {
		return Telefon;
	}

	public void setTelefon(String telefon) throws InvalidFormatException {
		if (!validTelefon(telefon)) throw new InvalidFormatException("Cannot convert", "Invalid phone number");
		Telefon = telefon;
	}

	public static Contact fromString(String str, String delim) throws InvalidFormatException
	{
		String[] s = str.split(delim);
		if (s.length!=4) throw new InvalidFormatException("Cannot convert", "Invalid data");
		if (!validTelefon(s[2])) throw new InvalidFormatException("Cannot convert", "Invalid phone number");
		if (!validName(s[0])) throw new InvalidFormatException("Cannot convert", "Invalid name");
		if (!validAddress(s[1])) throw new InvalidFormatException("Cannot convert", "Invalid address");
		if (!validAddress(s[3])) throw new InvalidFormatException("Cannot convert", "Invalid email");

		return new Contact(s[0], s[1], s[2], s[3]);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Name);
		sb.append(" ");
		sb.append(Address);
		sb.append(" ");
		sb.append(Telefon);
		sb.append(" ");
		sb.append(Email);
		return sb.toString();
	}
	
	private static boolean validName(String str)
	{
		
		String[] s = str.split("[\\p{Punct}\\s]+");
		if (s.length>2) return false;
		return true;
	}
	
	private static boolean validAddress(String str)
	{
		return true;
	}
	
	private static boolean validTelefon(String tel)
	{
		String[] s = tel.split("[\\p{Punct}\\s]+");
		if (tel.charAt(0) == '+' && s.length == 2 ) return true;
		if (tel.charAt(0) != '0')return false;
		if (s.length != 1) return false;
		return true;
	}
	
		
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Contact)) return false;
		Contact o = (Contact)obj;
		if (Name.equals(o.Name) && Email.equals(o.Email) &&
				Telefon.equals(o.Telefon))
			return true;
		return false;
	}
	
}
