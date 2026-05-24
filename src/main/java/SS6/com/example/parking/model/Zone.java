package SS6.com.example.parking.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "zones")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "occupied_spots")
    private Integer occupiedSpots = 0;

    // Quan hệ 1 - N với ParkingTicket (Phía nghịch đảo - Inverse side)
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ParkingTicket> parkingTickets;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public Integer getOccupiedSpots() { return occupiedSpots; }
    public void setOccupiedSpots(Integer occupiedSpots) { this.occupiedSpots = occupiedSpots; }
    public List<ParkingTicket> getParkingTickets() { return parkingTickets; }
    public void setParkingTickets(List<ParkingTicket> parkingTickets) { this.parkingTickets = parkingTickets; }
}