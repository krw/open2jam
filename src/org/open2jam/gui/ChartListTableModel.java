package org.open2jam.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.open2jam.parser.Chart;
import org.open2jam.parser.ChartList;

/**
 *
 * @author fox
 */
public class ChartListTableModel implements TableModel
{
    private ArrayList<ChartList> items;
    private final String[] col_names = new String[] { "Name", "Level", "Genre" };
    private int rank;
    
    private final ArrayList<TableModelListener> listeners;

    public ChartListTableModel()
    {
        listeners = new ArrayList<TableModelListener>();
        items = new ArrayList<ChartList>();
    }
    
    public void clear()
    {
        items.clear();
    }

    public void addRows(List<ChartList> l)
    {
        items.addAll(l);
        fireListeners();
    }

    public void setRawList(ArrayList<ChartList> list) {
        items = list;
        fireListeners();
    }

    public ArrayList<ChartList> getRawList() {
        return items;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
        fireListeners();
    }

    public ChartList getRow(int row)
    {
        return items.get(row);
    }

    public int getRowCount() {
        return items.size();
    }

    public int getColumnCount() {
       return col_names.length;
    }

    public String getColumnName(int columnIndex) {
        return col_names[columnIndex];
    }

    public Class<?> getColumnClass(int columnIndex) {
       switch(columnIndex)
        {
            case 0:return String.class;
            case 1:return Integer.class;
            case 2:return String.class;
        }
       return Object.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Chart c;
        if(items.get(rowIndex).isEmpty()) return null;
        if(items.get(rowIndex).size()-1 < rank)
            c = items.get(rowIndex).get(0);
        else
            c = items.get(rowIndex).get(rank);
        switch(columnIndex)
        {
            case 0:
                String str = c.getTitle();
                if(items.get(rowIndex).size()-1 < rank)
                    str = "[AUTO-EASY] "+str;
                return str;
            case 1:return c.getLevel();
            case 2:return c.getGenre();
        }
        return null;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Can't do that cowboy");
    }

    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    public void removeTableModelListener(TableModelListener l) {
       listeners.remove(l);
    }

    private void fireListeners() {
        TableModelEvent e = new TableModelEvent(this);
        fireListeners(e);
    }

    private void fireListeners(TableModelEvent e) {
        for(TableModelListener l : listeners)l.tableChanged(e);
    }
}
