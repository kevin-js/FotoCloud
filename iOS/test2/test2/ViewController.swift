//
//  ViewController.swift
//  test2
//
//  Created by John Ling on 8/2/15.
//  Copyright © 2015 John Ling. All rights reserved.
//

import UIKit
import Firebase

class ViewController: UIViewController {

    @IBOutlet weak var usernameInput: UITextField!
    @IBOutlet weak var passwordInput: UITextField!
    @IBOutlet weak var confirmInput: UITextField!
    @IBOutlet weak var status: UILabel!
    let ref = Firebase(url: "https://johnfotocloud.firebaseIO.com")
    
    @IBAction func signupNewUser(sender: AnyObject) {
        if usernameInput.text != "" && passwordInput.text != "" && confirmInput.text != ""{
            let username = usernameInput.text
            let password = passwordInput.text
            let confirm = confirmInput.text
            if password != confirm{
                status.text = "Passwords do not match"
                return
            }
            print(username)
            print(password)
            print(confirm)
            ref.createUser(username, password: password,
                withValueCompletionBlock: { error, result in
                    
                    if error != nil {
                        print("Error creating account")
                        self.status.text = "Error creating account"
                    } else {
                        let uid = result["uid"] as? String
                        print("Successfully created account with uid: \(uid)")
                        self.status.text = "Account created!"
                    }
            })
            
            
        }
        else{
            status.text = "Fill out all categories"
            return
        }
    }
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

