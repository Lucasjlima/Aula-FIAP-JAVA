package fiap.tds;

import fiap.tds.entities.Card;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/card")
public class CardResource {

    static List<Card> cards = new ArrayList<Card>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Card> getCards() {
        return cards;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Card getCard(@PathParam("id") int id){
        return cards.stream().filter(c -> c.getId() == id)
                .findFirst().orElse(null);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCard(Card card){
        cards.add(card);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCard(@PathParam("id") int id, Card card){
        cards.stream().filter(c -> c.getId() == id)
                .forEach(c -> {
                    c.setName(card.getName());
                    c.setDescription(card.getDescription());
                });
    }

    @DELETE
    @Path("/{id}")
    public void deleteCard(@PathParam("id") int id){
        cards.removeIf(c -> c.getId() == id);
    }
}
