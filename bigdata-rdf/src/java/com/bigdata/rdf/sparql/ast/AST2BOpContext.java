package com.bigdata.rdf.sparql.ast;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import com.bigdata.bop.engine.QueryEngine;
import com.bigdata.rdf.store.AbstractTripleStore;
import com.bigdata.service.IBigdataFederation;

/**
 * Convenience class for passing around the various pieces of context necessary
 * to construct the bop pipeline.
 */
public class AST2BOpContext {

	public final QueryRoot query;
	
	public final AtomicInteger idFactory;
	
	public final AbstractTripleStore db;
	
	public final QueryEngine queryEngine;
	
	public final Properties queryHints;
	
	public AST2BOpContext(final QueryRoot query,
			final AtomicInteger idFactory, final AbstractTripleStore db,
    		final QueryEngine queryEngine, final Properties queryHints) {
		
		this.query = query;
		this.idFactory = idFactory;
		this.db = db;
		this.queryEngine = queryEngine;
		this.queryHints = queryHints;
		
	}
	
	public int nextId() {
		
		return idFactory.incrementAndGet();
		
	}

	/**
	 * Return <code>true</code> if we are running on a cluster.
	 */
    public boolean isCluster() {

        return db.getIndexManager() instanceof IBigdataFederation<?>;

    }

    /**
     * Return the namespace of the lexicon relation.
     */
    public String getLexiconNamespace() {
        
        return db.getLexiconRelation().getNamespace();
        
    }
    
}
