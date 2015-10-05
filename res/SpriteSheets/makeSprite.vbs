Const DIMENSIONS = 31
CONST WIDTH  = 162
CONST HEIGTH = 164
Dim startingFolderName
Dim parentFolderPath

Call Main()

Sub Main()
	
	Set WshShell = CreateObject("WScript.Shell")
	strCurDir = WshShell.CurrentDirectory

	Set objFSO = CreateObject("Scripting.FileSystemObject")
	Set objSuperFolder = objFSO.GetFolder(strCurDir)
	startingFolderName = objSuperFolder.name
	Set resultFile = createResultFile(objFSO.GetFolder(objSuperFolder.Path))

	Call checkForSourceFolder()
	
	Call ShowSubfolders (objSuperFolder, objFSO, resultFile)
	
	Call closeResultFile(resultFile)	
	WScript.Quit 0
End Sub

Sub checkForSourceFolder()
	Dim hasParentFolder
	hasParentFolder = Msgbox("Is Your Script Contained within parent folder(s) within your  folder?", vbYesNo, "Script in res folder?")
	If hasParentFolder = vbYes Then
		parentFolderPath = InputBox("Parent Folders if any; please enter them here: "&vbNewLine&"(ex. parentFolder/subFolder)")
		If parentFolderPath = "" Then
			WScript.quit()
		End If
		parentFolderPath = "/" & parentFolderPath
	Else
		parentFolderPath = ""
	End If
End Sub

Sub ShowSubFolders(fFolder, objFSO, resultFile)
    Set objFolder = objFSO.GetFolder(fFolder.Path)
    Set colFiles = objFolder.Files
	
	For Each objFile in colFiles
        If UCase(objFSO.GetExtensionName(objFile.name)) = "PNG" Then
        	Dim input
			input = InputBox("Enter numeric sprite size for the image: (" & objFile.Name & ") spriteSheet") 
			If input = "" Then
				WScript.quit()
			End If
			Call getImageDimensions(fFolder.Path, objFile.name, input, resultFile)
        End If
    Next
	
    For Each Subfolder in fFolder.SubFolders
        Call ShowSubFolders(Subfolder, objFSO, resultFile)
    Next	
	
End Sub

Function createResultFile(folderPath)
	Set fileSave = CreateObject("Scripting.FileSystemObject")
    Set endResultFile = fileSave.CreateTextFile(folderPath & "\spriteSheetInfo.txt", True)	
	Set createResultFile = endResultFile
End Function

Sub closeResultFile(resultFile)
	resultFile.WriteLine("!")
	resultFile.close
End Sub

Sub getImageDimensions(oFolderPath, oFilePath, input, resultFile)
	Set oShell  = CreateObject ("Shell.Application")
	Set oFolder = oShell.Namespace (oFolderPath)
	Set oFile = oFolder.ParseName(oFilePath)
	Set objImage = CreateObject("WIA.ImageFile")
	
	objImage.LoadFile(oFolderPath & "\" & oFilePath)
	intWidth = objImage.Width
	intHeight = objImage.Height
	
	Set objRegEx = CreateObject("VBScript.RegExp")
	objRegEx.Global = True
	objRegEx.IgnoreCase = True
	objRegEx.Pattern = ".+(" & startingFolderName & ")"
	strNewString = objRegEx.Replace(oFolderPath, "")	
	curFilePath = Replace(strNewString, "\", "/") & "/" + oFile.Name
	spriteSheetName = Replace(oFile.Name, ".png", "")
	
	
	resultFile.WriteLine(".")
	resultFile.WriteLine(spriteSheetName)
	resultFile.WriteLine(parentFolderPath & curFilePath)	
	resultFile.WriteLine(input)
	resultFile.WriteLine(intWidth)
	resultFile.WriteLine(intHeight)
End Sub