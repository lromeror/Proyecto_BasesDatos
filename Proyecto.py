import random as rd
def print_account_options():
    print("=========================================")
    print("|           Account Options             |")
    print("=========================================")
    print("|   [1] Premium Account                 |")
    print("|       Yearly $7.49/mo 25% Off!        |")
    print("|       (Billed $89.88 annually)        |")
    print("|   [2] Monthly $9.99/mo                |")
    print("|                                       |")
    print("|   [3] Standard Account                |")
    print("|       Join the community.             |")
    print("|       Upgrade to MMF+ at any time.    |")
    print("=========================================")
    print("Choose an option: ", end="")
#print_account_options()
#option = input()
def print_buttons():
    print("+------+  +-------+")
    print("| JOIN |  | LOGIN |")
    print("+------+  +-------+")
    print("Press JOIN or LOGIN:", end=" ")

def JOIN_LOGIN():
    option = input().lower().strip()
    if option == "join":
        print_account_options()
        option = get_input("")
        if option == "3":
            register_user()
    elif option == 'login':
        print("LOGIN selected")
    else:
        print("Invalid option")

def get_input(prompt, required=True):
    user_input = input(prompt)
    while required and not user_input:
        user_input = input("This field is required. Please enter a value: ")
    return user_input

def register_user():
    print("Join the Community!\nRegister for free\n")
    
    username = get_input("Username: ")
    birthday_day = get_input("Birthday - Day [DD]: ")
    birthday_month = get_input("Birthday - Month [MM]: ")
    birthday_year = get_input("Birthday - Year [YYYY]: ")
    
    print("\nRegistration Options:")
    print("1. Next")
    print("2. Join with Google")
    print("3. Join with Facebook\n")
    
    option = get_input("Choose an option (1-3): ", required=False)
    while option in ["1","2","3"] : 
        if option == "1":
            print(f"Hi {username} Last step\n")
            name = get_input("Name: ")
            email = get_input("Email: ")
            password = get_input("Password: ")
            option=4
            login()
        elif option == "2":
            print("\nConnecting with Google")
            option=4
        elif option == "3":
            print("\nConnecting with Facebook")
            option=4
        else:
            print("\nInvalid option selected.")

def login():
    print("\nLog in to MyMiniFactory\n")
    
    username_or_email = get_input("Username or E-mail address: ")
    password = get_input("Password: ")
    
    print("=========================================")
    print("WELCOME TO MYMINIFACTORY")
    print("=========================================")

print_buttons()
JOIN_LOGIN()

def print_menu_options():
    print()
    print("|   [1] Join a Tribe                    |")
    print("|   Become part of a unique community   |")
    print("|                                       |")
    print("|   [2] Make a Purchase                 |")
    print("|   Browse our selection of goods       |")
    print("|                                       |")
    print("|   [3] Place an Order                  |")
    print("|   Order your custom items here        |")
    print("|                                       |")
    print("|   [4] Show ShoppingCar                |")
    print("=========================================")
    print("Choose an option: ", end="")


def process_payment(tribe_name):
    numero_ran = rd.randint(1,11)
    print(f"\nThe membership fee for joining the {tribe_name} tribe is ${numero_ran}.99 per month.")
    payment_method = get_input("Enter your payment method (e.g., credit card, PayPal): ")
    print(f"Processing your payment through {payment_method}...")
    print("Payment successful. You are now a member of the tribe!\n")
    print_menu_options()
    option = input()

def choose_tribe(tribes):
    for i, tribe in enumerate(tribes, start=1):
        print(f"[{i}] {tribe['name']} - {tribe['description']} - {tribe['members']} members")

    tribe_number = int(get_input("Enter the number of the tribe you want to join: "))
    while tribe_number < 1 or tribe_number > len(tribes):
        print("Invalid tribe number. Please try again.")
        tribe_number = int(get_input("Enter the number of the tribe you want to join: "))
    
    selected_tribe = tribes[tribe_number - 1]
    print(f"You have chosen to join the tribe {selected_tribe['name']}! Welcome!")
    process_payment(selected_tribe['name'])
    
def main_menu():
    tribes = [
        {'name': 'Witchsong', 'description': 'Miniatures', 'members': 6892},
        {'name': 'Big Bad Evil Guy', 'description': 'Roleplaying Games', 'members': 2409},
        {'name': 'Lord of the Print', 'description': 'High-Quality Prints', 'members': 2399},
        {'name': 'The Witchguild', 'description': 'Fantasy Adventures', 'members': 2210},
        {'name': 'Resinant Miniatures', 'description': 'Detailed Miniature Collections', 'members': 48},
        {'name': 'Velrock Art Miniatures', 'description': 'Stylized Fantasy Figures', 'members': 175},
        {'name': 'Lord of the Print', 'description': 'Epic Scale Miniatures', 'members': 2399},
        {'name': 'Infinite Dimensions Games', 'description': 'Innovative Game Miniatures', 'members': 507},
        {'name': 'Josh', 'description': 'Custom Designed Miniatures', 'members': 24},
        {'name': 'Dark Realms Forge', 'description': 'Dark Fantasy World Creations', 'members': 121},
        {'name': 'Little Shop of Sigil', 'description': 'Mythical Creature Sculptures', 'members': 6},
        {'name': 'Nikita Breder', 'description': 'Historical Miniature Scenes', 'members': 26},
    ]
    
    print("Welcome to the Tribe Joining Console!\n")
    print("Available Tribes to Join:\n")
    
    choose_tribe(tribes)
def upload_3d_object():
    print("Create a new 3D object\n")
    
    # Simulate file upload process
    print("Please specify the path to your 3D file.")
    file_path = get_input("Enter the file path: ")
    print(f"File {file_path} selected for upload.")
    
    # Enter details for the 3D object
    object_name = get_input("Enter a name for your object: ")
    object_description = get_input("Enter a description for your object: ")
    object_visibility = get_input("Set visibility (Public/Private): ").lower()
    

    print("\nUploading your 3D object...")
    print(f"Name: {object_name}")
    print(f"Description: {object_description}")
    print(f"Visibility: {'Public' if object_visibility == 'public' else 'Private'}")
    
    print("\nYour 3D object has been uploaded successfully!")

products = {
    'Fantasy': [
        {'name': 'Dragon Sculpture', 'price': 29.99},
        {'name': 'Wizard Miniature', 'price': 15.99},
    ],
    'Sci-Fi': [
        {'name': 'Spaceship Model', 'price': 39.99},
        {'name': 'Alien Figurine', 'price': 22.99},
    ],
    'Terrain': [
        {'name': 'Castle Ruins', 'price': 49.99},
        {'name': 'Futuristic Cityscape', 'price': 55.99},
    ],
    'Historical': [
        {'name': 'Roman Soldier', 'price': 8.99},
        {'name': 'Medieval Siege Engines', 'price': 30.99},
    ],
}
def read_and_addCompras (a,b):
    f = open("compras.txt","a")
    f.write(f"{a} -- {b}\n")
    f.close()

def display_products(category):
    print(f"\nProducts in category '{category}':")
    for i, product in enumerate(products[category], start=1):
        print(f"[{i}] {product['name']} - ${product['price']}")

def select_product(category):
    display_products(category)
    product_number = int(get_input(f"Select a product from the '{category}' category: "))
    while product_number < 1 or product_number > len(products[category]):
        print("Invalid product number. Please try again.")
        product_number = int(get_input(f"Select a product from the '{category}' category: "))
    
    selected_product = products[category][product_number - 1]
    print(f"You selected: {selected_product['name']} which costs ${selected_product['price']}")
    read_and_addCompras (selected_product['name'],selected_product['price'])
    menu_p()


def main_comprar():
    print("Welcome to the 3D Miniature Store!\n")
    print("Categories:")
    for i, category in enumerate(products, start=1):
        print(f"[{i}] {category}")

    category_number = int(get_input("Select a category to see products: "))
    category_name = list(products.keys())[category_number - 1]
    select_product(category_name)

def show_car():
    f = open("compras.txt","r")
    sun = 0
    cont = 0
    for l in f:
        lista = l.split("--")
        sun += float(lista[1])
        cont += 1
        print(l)
    f.close()

    print(f"El total de sus {cont} producto es {round(sun,2)}")

def menu_p():
    print_menu_options()
    option = input()
    if option == "1":
        main_menu()
    elif option == "2":
        upload_3d_object()
    elif option == "3":
        main_comprar()
    elif option == "4":
        show_car()
    else:
        print("\nInvalid option. Please try again.")




menu_p()


