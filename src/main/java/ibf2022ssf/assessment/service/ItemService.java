package ibf2022ssf.assessment.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ibf2022ssf.assessment.model.Item;
import ibf2022ssf.assessment.model.Quotation;

public class ItemService {
    public List<String> listOfItems(List<Item> cart){
        List<String> items = new LinkedList<String>();

        for (int i = 0; i < cart.size(); i++) {
            items.add(cart.get(i).getName());
        }

        return items;
    }

    public float totalCost(Quotation quotation,List<Item> cart){
        float totalCost = 0.0f;
        Map<String, Float> quotednos = quotation.getQuotations();
        for (int i = 0; i < cart.size(); i++) {
            String name = cart.get(i).getName();
            Float qty = (float)(cart.get(i).getQuantity());
            totalCost = totalCost + (qty*quotednos.get(name));
        }
        return totalCost;
    }
}
