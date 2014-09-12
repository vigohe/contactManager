package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import java.util.List;


/**
 * The persistent class for the COMPANY database table.
 * 
 */
@Entity
@Table(name="COMPANY")
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private int id;

	@Column(name="DESCRIPTION", nullable=false, length=128)
	@NotBlank(message="No debe estar en blanco.")
	private String description;

	@Column(name="NAME", nullable=false, length=64)
	@Size(min=3, max=12,message="Debe ser entre 3 y 12 carateres")
	private String name;

	//bi-directional many-to-one association to Contact prueba
	@OneToMany(mappedBy="company")
	private List<Contact> contacts;

	public Company() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Contact addContact(Contact contact) {
		getContacts().add(contact);
		contact.setCompany(this);

		return contact;
	}

	public Contact removeContact(Contact contact) {
		getContacts().remove(contact);
		contact.setCompany(null);

		return contact;
        

	}
	
	public String toString(){
		return "{"+this.id+","+this.name+","+this.description+"}";
	}
}