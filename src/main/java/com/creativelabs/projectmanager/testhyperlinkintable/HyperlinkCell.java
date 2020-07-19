package com.creativelabs.projectmanager.testhyperlinkintable;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;

public class HyperlinkCell implements  Callback<TableColumn<Item, Hyperlink>, TableCell<Item, Hyperlink>> {

    @Override
    public TableCell<Item, Hyperlink> call(TableColumn<Item, Hyperlink> arg) {
        TableCell<Item, Hyperlink> cell = new TableCell<Item, Hyperlink>() {
            /*@Override
            protected void updateItem(Hyperlink item, boolean empty) {
                setGraphic(item);
            }*/
            @Override
            protected void updateItem(Hyperlink item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : item);
                if (! empty) {
                    item.setOnAction(e -> {
                        Item test = new Item("das","ads");
                        Stage test2 = new Stage();
                        test.editTask(test2);


                    });
                }
            }
        };
        return cell;
    }
}
