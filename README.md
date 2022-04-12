# WaffleCorp e-commerce Store

This is a sample application for my presentation on Spring for GraphQL.

## In Memory DB

These are a number of GraphQL queries that can be run in the UI. 

```graphql
mutation AddProduct($input: ProductInput) {
    createProduct(input:$input) {
        id
        title
        desc
    }
}

mutation UpdateProduct($input: ProductInput) {
    updateProduct(input:$input) {
        id
        title
        desc
    }
}

mutation DeleteProduct($id:Int) {
    deleteProduct(id:$id)
}

query findAllProducts {
    allProducts {
        id
        title
        desc
    }
}

query findProduct($id:ID) {
    getProduct(id:$id) {
        id
        title
        desc
    }
}

query findAllOrders {
    allOrders {
        id
        status
        product {
            title
            desc
        }
        qty
        customer {
            firstName
            lastName
            email
        }
    }
}

query findAllCustomers {
    allCustomers {
        id
        firstName
        lastName
        email
        orders {
            id
            qty
            status
            product {
                title
            }
        }
    }
}


query findCustomerByLast {
    findCustomerByLastName(last:"abc") {
        id
        firstName
        lastName
        email
    }
}

query findAllReviews {
    allReviews {
        id
        content
        status
        product {
            title
        }
        customer {
            firstName
            lastName
        }
    }
}
```

```json
{
  "input": {
    "id": 2,
    "title": "new product",
    "desc": "my description"
  }
}
```