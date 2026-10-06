#علاء سليم عبد الرملاوي 
phone_numbers = []   

import os

user_name = os.environ.get("DIRECTORY_USERNAME", "demo")
main_password = os.environ.get("DIRECTORY_PASSWORD", "")

def log_in():
    if not main_password:
        print("Set DIRECTORY_PASSWORD before starting the application.")
        return False
    for tries_left in range(2, -1, -1):
        entered_user = input("Username: ")
        entered_password = input("Password: ")
        if entered_user == user_name and entered_password == main_password:
            print("Login successful!")
            return True
        print(f"Incorrect credentials. {tries_left} attempts left.")
    return False

def check_phone_number(phone):
    return phone.startswith("059") and len(phone) == 10 and phone.isdigit()

def check_id_number(id):
    return len(id) == 9 and id.isdigit()

def add_phone_number():
    while True:
        phone = input("Enter phone number : ")
        if not check_phone_number(phone):
            print("Invalid phone number format. Please try again.")
            continue
        
        id_num = input("Enter ID number : ")
        if not check_id_number(id_num):
            print("Invalid ID number format. Please try again.")
            continue
        
        name = input('Enter the name: ') 
        age = input('Enter the age: ')
        address = input('Enter the address: ')
        
        phone_info = {
            "phone": phone,
            "id": id_num,
            "name": name,
            "age": age,
            "address": address
        }
        phone_numbers.append(phone_info)
        print("Phone number added successfully!")
        break

def show_numbers():
    print("All Phone Numbers in Directory")
    if not phone_numbers:
        print("The directory is empty.")
        return
    for entry in phone_numbers:
        print(f"Phone: {entry['phone']}")
        print(f"ID: {entry['id']}")
        print(f"Name: {entry['name']}")
        print(f"Age: {entry['age']}")
        print(f"Address: {entry['address']}")
        print("-" * 20)

def search_number():
    print("Search for Phone Number")
    search_phone = input("Enter phone number to search: ")
    found = False
    for entry in phone_numbers:
        if entry['phone'] == search_phone:
            print("\nFound entry:")       
            print(f"Phone: {entry['phone']}")
            print(f"ID: {entry['id']}")
            print(f"Name: {entry['name']}")
            print(f"Age: {entry['age']}")
            print(f"Address: {entry['address']}")
            found = True
            break
    if not found:
        print("Phone number not found in directory")

def update_number():
    print("\nUpdate Phone Number Data")
    phone_to_update = input("Enter phone number to update: ")
    found = False
    for entry in phone_numbers:
        if entry['phone'] == phone_to_update:
            found = True
            print(f"1-Phone: {entry['phone']}")
            print(f"2-ID: {entry['id']}")
            print(f"3-Name: {entry['name']}")
            print(f"4-Age: {entry['age']}")
            print(f"5-Address: {entry['address']}")
            
            choice = input("Choose 1-5 to update or 0 to cancel: ")
            if choice == '1':
                new_phone = input("Enter new phone: ")
                if check_phone_number(new_phone):
                    entry['phone'] = new_phone
                    print("Phone number updated successfully.")
                else:
                    print("Invalid phone number format. Update cancelled.")
            elif choice == '2':
                new_id = input("Enter new ID: ")
                if check_id_number(new_id):
                    entry['id'] = new_id
                    print("ID updated successfully.")
                else:
                    print("Invalid ID format. Update cancelled.")
            elif choice == '3':
                entry['name'] = input('Enter new name: ')
                print("Name updated successfully.")
            elif choice == '4':
                entry['age'] = input('Enter new age: ')
                print("Age updated successfully.")
            elif choice == '5':
                entry['address'] = input('Enter new address: ')
                print("Address updated successfully.")
            elif choice == '0':
                print("Update cancelled.")
            else:
                print("Invalid choice. Update cancelled.")
            break
    if not found:
        print("Phone number not found in directory")

def delete_number():
    print("\nDelete number")
    phone_to_delete = input('Enter the number to delete: ')
    for i, entry in enumerate(phone_numbers):
        if entry['phone'] == phone_to_delete:
            del phone_numbers[i]
            print("Phone number deleted successfully.")
            return
    print("Phone number not found in directory")

def admin_dashboard():
    while True:
        print("\n--- Admin Dashboard ---")
        print("1. Add Phone Number")
        print("2. Show All Phone Numbers")
        print("3. Search for Phone Number")
        print("4. Update Phone Number Data")
        print("5. Delete Phone Number")
        print("6. Exit to Main Menu")
        
        choice = input('Enter your choice (1-6): ')
        if choice == '1':
            add_phone_number()
        elif choice == '2':
            show_numbers()
        elif choice == '3':
            search_number()
        elif choice == '4':
            update_number()
        elif choice == '5':
            delete_number()
        elif choice == '6':
            print("Returning to main menu...")
            break
        else:
            print("Invalid choice. Please try again.")

def main_menu():
    while True:
        print("\n*Welcome, the System is ready now*")
        print("1. Admin Dashboard")
        print("2. Search for details about phone number")
        print("3. Exit")
        
        choice = input("Enter your choice (1-3): ")
        
        if choice == '1':
            if log_in():
                admin_dashboard()
        elif choice == '2':
            search_number()
        elif choice == '3':
            print("Exiting system. Goodbye!")
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main_menu() 