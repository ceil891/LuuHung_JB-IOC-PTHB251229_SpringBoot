package SS6.com.example.parking.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;

    @Column(name = "color")
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VehicleType type;

    // Quan hệ 1 - N với ParkingTicket (Phía nghịch đảo - Inverse side)
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ParkingTicket> parkingTickets;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public VehicleType getType() { return type; }
    public void setType(VehicleType type) { this.type = type; }
    public List<ParkingTicket> getParkingTickets() { return parkingTickets; }
    public void setParkingTickets(List<ParkingTicket> parkingTickets) { this.parkingTickets = parkingTickets; }
}
