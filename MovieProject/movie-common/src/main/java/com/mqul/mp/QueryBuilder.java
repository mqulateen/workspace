package com.mqul.mp;


import javax.persistence.Query;

public class QueryBuilder {

    private Query query;

    private StringBuilder sb;
    private final static String SELECT = "SELECT";
    private final static String COUNT = "COUNT";

    private boolean firstWhere = true;
    private boolean firstSet = true;

    public QueryBuilder(String query)
    {
        sb = new StringBuilder();
        sb.append(query);
    }

    public QueryBuilder where(String l, String r)
    {
        if(firstWhere)
        {
            sb.append(String.format(" WHERE %s = '%s'", l, r));
            firstWhere = false;
        }
        else
        {
            sb.append(String.format(" AND %s = '%s'", l, r));
        }

        return this;
    }

    public QueryBuilder where(String l, int r)
    {
        if(firstWhere)
        {
            sb.append(String.format(" WHERE %s = %d", l, r));
            firstWhere = false;
        }
        else
        {
            sb.append(String.format(" AND %s = %d", l, r));
        }

        return this;
    }

    public QueryBuilder set(String l, String r)
    {
        if(firstSet)
        {
            sb.append(String.format(" SET %s = '%s'", l, r));
            firstSet = false;
        }
        else
        {
            sb.append(String.format(", %s = '%s'", l, r));
        }

        return this;
    }

    public String build()
    {
        return sb.toString();
    }
}
